package com.bootdo.fanfan.service.impl;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.domain.OrderDetialDO;
import com.bootdo.fanfan.domain.OrderStateDO;
import com.bootdo.fanfan.manager.order.OrderChain;
import com.bootdo.fanfan.manager.order.ValidateOrderOption;
import com.bootdo.fanfan.service.CommoditService;
import com.bootdo.fanfan.service.OrderDetialService;
import com.bootdo.fanfan.service.OrderStateService;
import com.bootdo.fanfan.vo.APIOrderDetail;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bootdo.fanfan.dao.OrderDao;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.service.OrderService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private EMapper eMapper;

	@Autowired
	private OrderStateService  orderStateService;

	@Autowired
	private OrderDetialService  orderDetialService;

	@Autowired
	private CommoditService commoditService;

	/**
	 * 创建订单
	 */
	@Transactional(rollbackFor = {SecurityException.class})
	public String createOrder(APIOrderRequVO orderVO){

		//验证
		validateOrder(orderVO);

		//获取订单主体信息
		OrderDO orderDO = eMapper.map(orderVO,OrderDO.class);

		//计算价格
		calculateOrder(orderVO,orderDO);

		//订单商品明细
		List<OrderDetialDO> orderDetialDOList =eMapper.mapArray(orderVO.getDetailList(),OrderDetialDO.class);

		//1.订单是否创建
		if(StringUtils.isEmpty(orderVO.getOrderNum()))
		{
			//生成订单号
			orderDO.setOrderNum(this.getOrderNum(orderDO.getCreateId()));
			//获取实体
			orderDO.setOrderState(101);
			orderDO.setCreateTime(Calendar.getInstance().getTime());
			//保存订单
			save(orderDO);
		}

		//设置订单号
		orderDetialDOList.forEach(f->{ f.setOrderId(orderDO.getId()); });

		//保存订单详情
		orderDetialService.batchSave(orderDetialDOList);

		//保存订单状态
		orderStateService.save(new OrderStateDO(orderDO.getId(),orderDO.getOrderState(),orderDO.getCreateId()));

		return orderDO.getOrderNum();
	}

	/**
	 * 查询订单
	 * @param orderNum
	 */
	public APIOrderRequVO queryOrder(String orderNum){

		OrderDO orderDO  =  orderDao.getByOrderNum(orderNum);

		if(orderDO==null)
			return null;

		//查询商品详情
		List<OrderDetialDO> orderDetialDOList = orderDetialService.queryByOrderId(orderDO.getId());

		//对象转换
		APIOrderRequVO apiOrderRequVO =  eMapper.map(orderDO,APIOrderRequVO.class);

		//订单详情转换
		apiOrderRequVO.setDetailList(eMapper.mapArray(orderDetialDOList,APIOrderDetail.class));

		return  apiOrderRequVO;
	}

	@Override
	public OrderDO get(Integer id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Integer id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderDao.batchRemove(ids);
	}


	//region  私有方法

	/**
	 * 验证订单
	 * @param orderRequVO
	 */
	private void  validateOrder(APIOrderRequVO orderRequVO){
		if(orderRequVO==null){
			throw new SecurityException("订单信息不能为空");
		}

		if(orderRequVO.getDetailList()==null || orderRequVO.getDetailList().size()==0){
			throw new SecurityException("订单商品信息不能为空");
		}

		if(orderRequVO.getCreateId()==null){
			throw  new SecurityException("用户信息不能为空");
		}

		if(orderRequVO.getOrderState()==null || orderRequVO.getOrderState()==101 ){
			return;
		}
	}

	/**
	 * 计算价格
	 * @param orderDO
	 * @param orderRequVO
	 */
	private void  calculateOrder(APIOrderRequVO orderRequVO,OrderDO  orderDO) {

		//用户提交的的商品Id
		List<Integer> commodityIdArry = orderRequVO.getDetailList()
				.stream()
				//outType:1 商品
				.filter(f->{return(f.getOutType()!=null  && f.getOutType()==1);})
				//获取商品 id
				.map(m -> m.getOutId()).collect(Collectors.toList());

		//查询所有有效的商品
		List<CommoditDO> commoditDOList = commoditService.queryByIdarry(commodityIdArry);

		//提交的商品信息不对
		if (commodityIdArry.size() != commoditDOList.size()) {
			//有效的商品Id
			List<Integer> validComodityIdArry = commoditDOList.stream().map(m -> m.getId()).collect(Collectors.toList());

			//查询失效的商品Id
			List<Integer> unvalidComodityIdArry = commodityIdArry.stream().filter(f -> {
				return !validComodityIdArry.contains(f);
			}).collect(Collectors.toList());

			throw new SecurityException("商品已售空" + unvalidComodityIdArry);
		}

		// 商品总数量
		Integer commodityTotal = 0;

		//商品总额
		BigDecimal orderTotail = new BigDecimal(0);

		//计算总数量
		for (APIOrderDetail detail : orderRequVO.getDetailList()) {

			if (detail.getOutType() == null || detail.getOutType() != 1)
				continue;

			//计算总数量
			commodityTotal += detail.getOutSize();

			for (CommoditDO itemDo : commoditDOList) {
				if (itemDo.getId().equals(detail.getOutId())) {
					//总额 加 商品价格 乘 商品数量
					orderTotail = orderTotail.add(itemDo.getCommoditSalePrice().multiply(new BigDecimal(detail.getOutSize())));
					detail.setOutTitle(itemDo.getCommoditTitle());
					detail.setOutPrice(itemDo.getCommoditSalePrice());
				}
			}
		}
		//设置总数量
		orderDO.setOrderCommodityTotal(commodityTotal);
		//设置优惠总额
		orderDO.setOrderDiscountTotal(new BigDecimal(0));
		//设置订单总额
		orderDO.setOrderTotal(orderTotail);
		//设置支付总额  订单总额-支付总额
		orderDO.setOrderPay(orderDO.getOrderTotal().subtract(orderDO.getOrderDiscountTotal()));
	}

	private String getOrderNum(Integer userId){
		Long time = System.currentTimeMillis();

		return time+""+(userId*100231%100000);
	}

}
