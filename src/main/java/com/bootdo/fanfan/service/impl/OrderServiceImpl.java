package com.bootdo.fanfan.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.*;
import com.bootdo.fanfan.domain.enumDO.OrderDetailEnum;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.service.*;
import com.bootdo.fanfan.vo.*;
import com.bootdo.fanfan.vo.model.XGPushModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bootdo.fanfan.dao.OrderDao;
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

	@Autowired
	private OrderReceiverService  orderReceiverService;

	@Autowired
	private AlipayManager alipayManager;

	@Autowired
	private OrderAlipayService  orderAlipayService;

	@Autowired
	private BootdoConfig bootdoConfig;

	@Autowired
	XGPushManager xgPushManager;

	@Autowired
	RedisUtils redisUtils;

	//region 默认方法
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

	//endregion

	/**
	 * 创建订单
	 */
	@Override
	@Transactional(rollbackFor = {SecurityException.class,Exception.class})
	public Integer createOrder(APIOrderRequVO orderVO){

		//验证
		validateOrder(orderVO);

		//获取订单主体信息
		OrderDO orderDO = eMapper.map(orderVO,OrderDO.class);

		//计算价格
		calculateOrder(orderVO,orderDO);

		//订单商品明细
		List<OrderDetialDO> orderDetialDOList =eMapper.mapArray(orderVO.getDetailList(),OrderDetialDO.class);

		//收货人地址
		OrderReceiverDO orderReceiverDO = eMapper.map(orderVO.getReceiver(),OrderReceiverDO.class);

		//1.订单是否创建
		if(StringUtils.isEmpty(orderVO.getOrderNum()))
		{
			//生成订单号
			orderDO.setOrderNum(this.getOrderNum(orderDO.getCustomerId()));
			//获取实体
			orderDO.setOrderState(OrderStateEnum.userCreate.getVal());
			orderDO.setCreateTime(Calendar.getInstance().getTime());
			//保存订单
			save(orderDO);
		}else{
			//修改订单
			update(orderDO);
		}

		//设置订单号
		orderDetialDOList.forEach(f->{ f.setOrderId(orderDO.getId()); });

		//保存订单详情
		orderDetialService.batchSave(orderDetialDOList);

		//保存订单状态
		orderStateService.save(new OrderStateDO(orderDO.getId(),orderDO.getOrderState(),orderDO.getCustomerId()),orderDO.getOrderNum());

		//保存订单收货人信息
		orderReceiverDO.setId(orderDO.getId());
		orderReceiverService.save(orderReceiverDO);

		//创建支付宝预付单
        if(orderDO.getOrderState().equals(OrderStateEnum.userRequestPay.getVal())) {
        	//创建预付单成功
            if(createAlipayOrder(orderVO, orderDO)){
            	//订单状态
            	orderDO.setOrderState(OrderStateEnum.userWaitPay.getVal());
            	//更新订单状态
				updateOrderState(orderDO);
			}
        }

		return orderDO.getId();
	}

	/**
	 * 查询订单
	 * @param orderId
	 */
	@Override
	public APIOrderRequVO queryOrder(Integer orderId){

		OrderDO orderDO  =  orderDao.get(orderId);

		if(orderDO==null) {
			return null;
		}

		//查询商品详情
		List<OrderDetialDO> orderDetialDOList = orderDetialService.queryByOrderId(orderDO.getId());

		//对象转换
		APIOrderRequVO apiOrderRequVO =  eMapper.map(orderDO,APIOrderRequVO.class);

		//订单详情转换
		apiOrderRequVO.setDetailList(eMapper.mapArray(orderDetialDOList,APIOrderDetailVO.class));

		//订单收货人信息
		OrderReceiverDO receiverDO = orderReceiverService.queryById(orderDO.getId());
		if(receiverDO!=null){
			apiOrderRequVO.setReceiver(eMapper.map(receiverDO, APIOrderReceiverVO.class));
		}

		//请求支付时--创建支付宝预付单
       if(orderDO.getOrderState().equals(OrderStateEnum.userWaitPay.getVal())){
			apiOrderRequVO.setAlipayOrderStr(orderAlipayService.getOrderStrById(orderDO.getId()));
		    apiOrderRequVO.setLastPayTime(getLastPaySecond(orderDO.getId()));
       }

       //主体图片
       apiOrderRequVO.setMainImg(bootdoConfig.getStaticUrl()+orderDetialService.queryCommodityImgByOrderId(orderDO.getId()));

		return  apiOrderRequVO;
	}


	/**
	 * 修改订单状态
	 * @param orderDO
	 */
	@Override
	@Transactional(rollbackFor = {SecurityException.class})
	public void updateOrderState(OrderDO orderDO){
		//保存订单状态
		orderStateService.save(new OrderStateDO(orderDO.getId(),orderDO.getOrderState(),orderDO.getCustomerId()),orderDO.getOrderNum());
		//修改订单状态
		if(orderDO.getOrderState().equals(OrderStateEnum.userPaid.getVal())){
			//查询customerId
			Integer customerId = orderDao.getCustomerIdById(orderDO.getId());
			String redisKey = dateNumKey(customerId);
			Long dateNum = (Long)redisUtils.get(redisKey);
			//加一
			dateNum = dateNum==null?10001L:(++dateNum);
			//更新订单信息
			orderDao.updateOrderStatePay(orderDO.getOrderState(),orderDO.getId(),"A"+dateNum);
			redisUtils.set(redisKey,dateNum);
		}
		orderDao.updateOrderState(orderDO.getOrderState(),orderDO.getId());
	}

	@Override
	public OrderDO queryOrderByOrdernum(String orderNum) {
		return orderDao.getIdByOrderNum(orderNum);
	}

	/**
	 * 查询订单状态
	 * @param idArray
	 * @return
	 */
	@Override
	public List<OrderDO> getStateById(List<Integer> idArray) {
		return orderDao.getStateById(idArray);
	}

	/**
	 * 发送订单消息通知
	 * @param orderId
	 */
	@Override
	public void sendOrderNotification(Integer orderId) {

		OrderDO orderDO = this.get(orderId);
		if(orderDO==null){
			return;
		}

		XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.payOrder,orderDO.getCustomerId().longValue());
		pushModel.setMsgTitle("您有新的订单");
		pushModel.setMsgContent("订单总额："+orderDO.getOrderTotal().toString());
		pushModel.addParams("orderId",orderId);

		//推送消息
		xgPushManager.put(pushModel);
	}

	/**
	 * 查询用户订单
	 * @param map
	 * @return
	 */
	@Override
	public List<APIOrderListVO> queryOrderByUser(Map<String, Object> map) {

		List<APIOrderListVO> list = orderDao.queryOrderByUser(map);

		if(list!=null && list.size()>0){
			list.forEach((item)->{
				//设置文本
				item.setOrderStateText(OrderStateEnum.get(item.getOrderState()).getText());
				item.setCommoditImg(bootdoConfig.getStaticUrl()+item.getCommoditImg());
				//待支付状态
				if(item.getOrderState()==OrderStateEnum.userWaitPay.getVal()){
					//剩余支付秒数
					item.setLastPayTime(getLastPaySecond(item.getId()));
					//支付宝支付字符串
					item.setAlipayOrderStr(orderAlipayService.getOrderStrById(item.getId()));
				}
			});
		}

		return list;
	}

	@Override
	public List<APIOrderListCustomerVO> queryOrderByCustomer(Map<String, Object> map) {

		List<APIOrderListCustomerVO> list = orderDao.queryOrderByCustomer(map);

		if(list!=null && list.size()>0){
			List<Integer> idArray = list.stream().map(APIOrderListCustomerVO::getId).collect(Collectors.toList());
			//查询订单商品
			List<OrderDetialDO> detialDOList = orderDetialService.queryByOrderIdArray(idArray);

			//遍历
			if(detialDOList!=null && detialDOList.size()>0){
				//遍历订单
				list.forEach((item)->{
					//获取订单对应的商品
					List<OrderDetialDO> temList = detialDOList.stream().filter((detail)-> detail.getOrderId().equals(item.getId())).collect(Collectors.toList());

					//设置订单详情
					if(temList!=null && temList.size()>0){
						item.setDetails(eMapper.mapArray(temList,APIOrderDetailVO.class));
						//设置商品数量
						Integer commoditySize = (int)temList.stream().filter((commodity) -> OrderDetailEnum.Commodity.getVal().equals(commodity.getOutType())).count();
						item.setCommoditySize(commoditySize);
					}

					//设置状态文本
					item.setOrderStateText(OrderStateEnum.get(item.getOrderState()).getText());
				});
			}
		}

		return list;
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

		if(orderRequVO.getUserId()==null){
			throw  new SecurityException("用户信息不能为空");
		}

		if(orderRequVO.getOrderState()==null || orderRequVO.getOrderState()==101 ){
			return;
		}

		if(orderRequVO.getOrderState()>101  && StringUtils.isEmpty(orderRequVO.getOrderNum())){
			throw new SecurityException("无效的订单状态");
		}

		//查询订单
		OrderDO orderDO =orderDao.getIdByOrderNum(orderRequVO.getOrderNum());

        if(orderDO==null) {
            throw new SecurityException("无效的订单号");
        }

        //设置订单状态
		if(orderRequVO.getOrderState()<orderDO.getOrderState()){
        	throw new SecurityException("无效的订单状态");
		}

        //设置Id
		orderRequVO.setId(orderDO.getId());

        //判断状态是否可已修改
        int [] notModifyState= new int[]{
                OrderStateEnum.orderSuccess.getVal(),
                OrderStateEnum.userPayOvertime.getVal(),
        };

        if(Arrays.asList(notModifyState).contains(orderRequVO.getOrderState())) {
            throw new SecurityException(OrderStateEnum.get(orderRequVO.getOrderState()).getText());
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
		for (APIOrderDetailVO detail : orderRequVO.getDetailList()) {

			if (detail.getOutType() == null || detail.getOutType() != 1) {
				continue;
			}

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

    /**
     * 创建支付宝预付单
     * @param orderRequVO
     * @param orderDO
     */
	private boolean createAlipayOrder(APIOrderRequVO orderRequVO,OrderDO  orderDO){

	    OrderAlipayDO alipayDO =  new OrderAlipayDO();
	    alipayDO.setId(orderDO.getId());
	    alipayDO.setBody(StringUtils.join(orderRequVO.getDetailList().stream().map(m->m.getOutTitle()).toArray()));
        alipayDO.setGoodsType("1");
        alipayDO.setCreateTime(Calendar.getInstance().getTime());
        alipayDO.setPassbackParams(orderDO.getId().toString());
        alipayDO.setProductCode("QUICK_MSECURITY_PAY");
        alipayDO.setStoreId("");
        alipayDO.setSubject("饭饭点餐");
        alipayDO.setTimeoutExpress("15m");
        alipayDO.setTotalAmount(orderDO.getOrderTotal().toString());
        alipayDO.setTradeNo(orderDO.getOrderNum());

        String backStr = alipayManager.createTradePay(alipayDO);
        if(backStr==""){
            throw  new  SecurityException("创建支付宝预付单失败");
        }
        alipayDO.setCreateBackBody(backStr);

        //保存
        return orderAlipayService.save(alipayDO)>0;
    }

    /**
     * 获取订单号
     * @param userId
     * @return
     */
	private String getOrderNum(Integer userId){
		Long time = System.currentTimeMillis();

		return time+""+(userId*100231%100000);
	}


	/**
	 * 获取剩余支付秒数
	 * @return
	 */
	private Long getLastPaySecond(Integer orderId) {
		//提交预付单时间
		Date submitOrderTime = orderStateService.queryStateDate(orderId, OrderStateEnum.userRequestPay.getVal());
		//加15分钟
		submitOrderTime.setMinutes(submitOrderTime.getMinutes()+15);
		//最后付款剩余秒数
		return (submitOrderTime.getTime() - System.currentTimeMillis())/1000;
	}

	private String dateNumKey(Integer customerId){
		return "date_num_"+customerId+"_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	//endregion

}
