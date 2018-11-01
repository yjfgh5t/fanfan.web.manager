package com.bootdo.fanfan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.exception.BDException;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.*;
import com.bootdo.fanfan.domain.DTO.OrderStatisticsDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgMQDTO;
import com.bootdo.fanfan.domain.enumDO.OrderDetailEnum;
import com.bootdo.fanfan.domain.enumDO.OrderDetailType;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.domain.enumDO.OrderTypeEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.manager.TemplateMsgManager;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.service.*;
import com.bootdo.fanfan.vo.*;
import com.bootdo.fanfan.vo.model.XGPushModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.bootdo.fanfan.dao.OrderDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(AlipayManager.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private EMapper eMapper;

	@Autowired
	private OrderStateService  orderStateService;

	@Autowired
	private OrderDetialService  orderDetialService;

	@Autowired
	private CommodityService commodityService;

	@Autowired
	private OrderReceiverService  orderReceiverService;

	@Autowired
	private AlipayManager alipayManager;

	@Autowired
	private OrderAlipayService  orderAlipayService;

	@Autowired
	private ShopService shopService;

	@Autowired
	XGPushManager xgPushManager;

	@Autowired
	TemplateMsgManager templateMsgManager;

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
	@Transactional(rollbackFor = {Exception.class})
	public Integer createOrder(APIOrderRequVO orderVO){

		//计算价格、转换信息
		calculateOrder(orderVO);

		//获取订单主体信息
		OrderDO orderDO = eMapper.map(orderVO,OrderDO.class);
		//订单商品明细
		List<OrderDetailDO> orderDetailDOList =eMapper.mapArray(orderVO.getDetailList(),OrderDetailDO.class);
		//收货人地址
		OrderReceiverDO orderReceiverDO = eMapper.map(orderVO.getReceiver(),OrderReceiverDO.class);

		//1.订单是否创建
		if(StringUtils.isEmpty(orderDO.getOrderNum()))
		{
			//生成订单号
			orderDO.setOrderNum(this.getOrderNum());
			//获取实体
			orderDO.setOrderState(OrderStateEnum.userRequestPay.getVal());
			orderDO.setCreateTime(Calendar.getInstance().getTime());
			//保存订单
			save(orderDO);
		}else{
			//修改订单
			update(orderDO);
		}

		//设置订单号
		orderDetailDOList.forEach(f->{ f.setOrderId(orderDO.getId()); });

		//保存订单详情
		orderDetialService.batchSave(orderDetailDOList);

		//保存订单状态
		orderStateService.save(new OrderStateDO(orderDO.getId(),orderDO.getOrderState(),orderDO.getCustomerId()),orderDO.getOrderNum());

		//保存订单收货人信息
		if(orderReceiverDO!=null && !StringUtils.isEmpty(orderReceiverDO.getAddr())) {
			orderReceiverDO.setId(orderDO.getId());
			orderReceiverService.save(orderReceiverDO);
		}

		//创建支付宝预付单
        if(orderDO.getOrderState().equals(OrderStateEnum.userRequestPay.getVal())) {
        	//创建预付单成功
            if(createAlipayOrder(orderDetailDOList, orderDO)){
            	//订单状态
            	orderDO.setOrderState(OrderStateEnum.userWaitPay.getVal());
				orderDO.setCustomerId(orderDO.getCustomerId());
            	//更新订单状态
				updateOrderState(orderDO);
			}
        }

		return orderDO.getId();
	}

	/**
	 * 计算价格
	 * @param orderRequVO
	 */
	@Override
	public APIOrderRequVO calculateOrder(APIOrderRequVO orderRequVO) {

		//验证
		validateOrder(orderRequVO);

		//用户提交的的商品Id
		List<Integer> commodityIdArray = orderRequVO.getDetailList()
				.stream()
				//outType[1:商品 5:商品規格]
				.filter(f->{return(f.getOutType()!=null  && (OrderDetailType.Commodity.getId().equals(f.getOutType()) || OrderDetailType.CommodityNorms.getId().equals(f.getOutType())));})
				//获取商品 id
				.map(m -> m.getCommodityId()).collect(Collectors.toList());

		//去除重复id
		HashSet<Integer> hashSet = new HashSet<>(commodityIdArray);
		commodityIdArray.clear();
		commodityIdArray.addAll(hashSet);

		//查询所有有效的商品
		List<CommodityWidthExtendDO> commodityDOList = commodityService.queryByIdArray(commodityIdArray);

		//提交的商品信息不对
		if (commodityIdArray.size() != commodityDOList.size()) {
			//有效的商品Id
			List<Integer> validCommodityIdArray = commodityDOList.stream().map(m -> m.getId()).collect(Collectors.toList());

			//查询失效的商品Id
			List<Integer> unvalidComodityIdArry = commodityIdArray.stream().filter(f -> {
				return !validCommodityIdArray.contains(f);
			}).collect(Collectors.toList());

			throw new BDException("商品已售空" + unvalidComodityIdArry,BDException.VERIFY_ERROR_CODE);
		}

		// 商品总数量
		Integer commodityTotal = 0;

		//商品总额
		BigDecimal orderTotal = new BigDecimal(0),packageTotal = new BigDecimal(0),zeroDecimal = new BigDecimal(0);

		//计算总数量
		for (APIOrderDetailVO detail : orderRequVO.getDetailList()) {
			if (OrderDetailType.Commodity.getId().equals(detail.getOutType()) || OrderDetailType.CommodityNorms.getId().equals(detail.getOutType())) {
				//计算总数量
				commodityTotal += detail.getOutSize();
				for (CommodityWidthExtendDO itemDo : commodityDOList) {
					if (itemDo.getId().equals(detail.getCommodityId())) {
						//总额 加 商品价格 乘 商品数量
						BigDecimal price =null;

						//普通商品
						if(OrderDetailType.Commodity.getId().equals(detail.getOutType())){
							price = itemDo.getCommoditySalePrice();
							detail.setOutTitle(itemDo.getCommodityTitle());
							detail.setOutPrice(itemDo.getCommoditySalePrice());
						}else if(OrderDetailType.CommodityNorms.getId().equals(detail.getOutType())) {
							//规格商品
							if (!CollectionUtils.isEmpty(itemDo.getExtendList())) {
								//查询符合规则数据
								CommodityExtendDO normalModel = itemDo.getExtendList().stream().filter((f) -> f.getId().equals(detail.getOutId())).findFirst().get();
								if(normalModel!=null){
									price = normalModel.getCommodityPrice();
									detail.setOutTitle(itemDo.getCommodityTitle()+"-"+normalModel.getTitle());
									detail.setOutPrice(normalModel.getCommodityPrice());
								}
							}
						}

						//打包费用
						if(zeroDecimal.compareTo(itemDo.getCommodityPackagePrice())!=0){
							//打包费用乘以商品数量
							packageTotal = packageTotal.add(itemDo.getCommodityPackagePrice().multiply(new BigDecimal(detail.getOutSize())));
						}

						if(price!=null) {
							//商品价格乘以商品数量
							orderTotal = orderTotal.add(price.multiply(new BigDecimal(detail.getOutSize())));
						}
					}
				}
			}
		}

		//添加堂吃 打包费用
		if(OrderTypeEnum.Pack.getVal().equals(orderRequVO.getOrderType()) && zeroDecimal.compareTo(packageTotal)!=0){
			APIOrderDetailVO packageModel = new APIOrderDetailVO();
			packageModel.setOutTitle("餐盒");
			packageModel.setOutPrice(packageTotal);
			packageModel.setCommodityId(0);
			packageModel.setOutId(0);
			packageModel.setOutSize(0);
			packageModel.setOutType(OrderDetailType.Package.getId());
			//去除已经包含的餐盒费
			orderRequVO.getDetailList().removeIf(item -> OrderDetailType.Package.getId().equals(item.getOutType()));
			orderRequVO.getDetailList().add(packageModel);
			//添加餐盒费用
			orderTotal = orderTotal.add(packageTotal);
		}


		//设置总数量
		orderRequVO.setOrderCommodityTotal(commodityTotal);
		//设置优惠总额
		orderRequVO.setOrderDiscountTotal(new BigDecimal(0));
		//设置订单总额
		orderRequVO.setOrderTotal(orderTotal);
		//设置支付总额  订单总额-支付总额
		orderRequVO.setOrderPay(orderRequVO.getOrderTotal().subtract(orderRequVO.getOrderDiscountTotal()));

		return orderRequVO;
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
		List<OrderDetailDO> orderDetailDOList = orderDetialService.queryByOrderId(orderDO.getId());

		//对象转换
		APIOrderRequVO apiOrderRequVO =  eMapper.map(orderDO,APIOrderRequVO.class);

		//订单详情转换
		apiOrderRequVO.setDetailList(eMapper.mapArray(orderDetailDOList,APIOrderDetailVO.class));

		if(orderDO.getOrderType()!=null){
			apiOrderRequVO.setOrderTypeText(OrderTypeEnum.get(orderDO.getOrderType()).getText());
		}

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
       apiOrderRequVO.setMainImg(orderDetialService.queryCommodityImgByOrderId(orderDO.getId()));

		return  apiOrderRequVO;
	}


	/**
	 * 修改订单状态
	 * @param orderDO
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void updateOrderState(OrderDO orderDO){
		//保存订单状态
		if(orderStateService.save(new OrderStateDO(orderDO.getId(),orderDO.getOrderState(),orderDO.getCustomerId()),orderDO.getOrderNum())>0) {
			//顾客支付
			if (orderDO.getOrderState().equals(OrderStateEnum.userPaid.getVal())) {
				//查询customerId
				Integer customerId = orderDao.getCustomerIdById(orderDO.getId());
				String dateNum = dateNum(customerId);
				//更新订单信息
				orderDao.updateOrderStatePay(orderDO.getOrderState(), orderDO.getId(), dateNum);
				//推送消息队列-下单通知-给顾客
				templateMsgManager.put(new TemplateMsgMQDTO().buildOrderPayMQ(orderDO.getId()));
				//推送下单通知-给商户
				sendOrderNotification(orderDO.getId());
				//商户取消订单
			} else if (orderDO.getOrderState().equals(OrderStateEnum.businessCancel)) {
				//更新状态
				if (orderDao.updateOrderStateCancel(orderDO.getOrderState(), orderDO.getId(), orderDO.getOrderCustomerRemark()) > 0) {
					//支付宝退款
					if (!alipayManager.tradeRefund(orderDO.getOrderNum(), orderDO.getOrderPay().doubleValue(), null)) {
						throw new BDException("支付宝退款失败",BDException.BUSINESS_ERROR_CODE);
					}
					//推送消息队列-取消订单通知
					templateMsgManager.put(new TemplateMsgMQDTO().buildOrderCancleMQ(orderDO.getId()));
				} else {
					//支付宝退款成功 订单修改失败
					throw new BDException("退款失败，请稍后重试",BDException.BUSINESS_ERROR_CODE);
				}
			} else {
				//其它交易状态
				orderDao.updateOrderState(orderDO.getOrderState(), orderDO.getId());
			}
		}
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

		APIOrderRequVO orderRequVO = this.queryOrder(orderId);
		if(orderRequVO==null){
			return;
		}
		XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.payOrder,orderRequVO.getCustomerId().longValue());
		pushModel.setMsgTitle("您有新的订单");
		pushModel.setMsgContent("订单总额："+orderRequVO.getOrderTotal().doubleValue());

		//数据转换
		APIPrintOrderVO data = eMapper.map(orderRequVO, APIPrintOrderVO.class);
		if(orderRequVO.getDetailList()!=null){
			List<APIPrintOrderDetailVO> detailVOS = eMapper.mapArray(orderRequVO.getDetailList(), APIPrintOrderDetailVO.class);
			data.setDetails(detailVOS);
		}
		pushModel.addParams("data", JSONObject.toJSONString(data));
		pushModel.setNotification(false);
		//推送消息
		xgPushManager.put(pushModel);
	}

	@Override
	public Integer getCustomerIdById(Integer id) {
		return orderDao.queryCustomerIdById(id);
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
				item.setCommoditImg(item.getCommoditImg());
				//待支付状态
				if(item.getOrderState().equals(OrderStateEnum.userWaitPay.getVal())){
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
			List<OrderDetailDO> detialDOList = orderDetialService.queryByOrderIdArray(idArray);

			//遍历
			if(detialDOList!=null && detialDOList.size()>0){
				//遍历订单
				list.forEach((item)->{
					//获取订单对应的商品
					List<OrderDetailDO> temList = detialDOList.stream().filter((detail)-> detail.getOrderId().equals(item.getId())).collect(Collectors.toList());

					//设置订单详情
					if(temList!=null && temList.size()>0){
						item.setDetails(eMapper.mapArray(temList,APIOrderDetailVO.class));
						//设置商品数量
						Integer commoditySize = (int)temList.stream().filter((commodity) -> OrderDetailEnum.Commodity.getVal().equals(commodity.getOutType())).count();
						item.setCommoditySize(commoditySize);
					}

					//设置状态文本
					item.setOrderStateText(OrderStateEnum.get(item.getOrderState()).getText());
					//堂吃类型文本
					item.setOrderTypeText(OrderTypeEnum.get(item.getOrderType()).getText());
				});
			}
		}

		return list;
	}

	/**
	 * 查询订单统计信息
	 * @param map
	 * @return
	 */
	@Override
	public OrderStatisticsDTO queryOrderStatistics(Map<String,Object> map){

		return orderDao.queryOrderStatistics(map);
	}


	//region  私有方法

	/**
	 * 验证订单
	 * @param orderRequVO
	 */
	private void  validateOrder(APIOrderRequVO orderRequVO){
		if(orderRequVO==null){
			throw new BDException("订单信息不能为空",BDException.VERIFY_ERROR_CODE);
		}

		if(orderRequVO.getDetailList()==null || orderRequVO.getDetailList().size()==0){
			throw new BDException("订单商品信息不能为空",BDException.VERIFY_ERROR_CODE);
		}

		if(orderRequVO.getUserId()==null){
			throw  new BDException("用户信息不能为空",BDException.VERIFY_ERROR_CODE);
		}

		if(orderRequVO.getOrderState()==null || orderRequVO.getOrderState()==101 ){
			return;
		}

		//查询订单
		OrderDO orderDO =orderDao.getIdByOrderNum(orderRequVO.getOrderNum());

        if(orderDO==null) {
            throw new BDException("无效的订单号",BDException.VERIFY_ERROR_CODE);
        }

        //设置订单状态
		if(orderRequVO.getOrderState()<orderDO.getOrderState()){
        	throw new BDException("无效的订单状态",BDException.VERIFY_ERROR_CODE);
		}

        //设置Id
		orderRequVO.setId(orderDO.getId());

        //判断状态是否可已修改
        int [] notModifyState= new int[]{
                OrderStateEnum.orderSuccess.getVal(),
                OrderStateEnum.userPayOvertime.getVal(),
        };

        if(Arrays.asList(notModifyState).contains(orderRequVO.getOrderState())) {
            throw new BDException(OrderStateEnum.get(orderRequVO.getOrderState()).getText(),BDException.BUSINESS_ERROR_CODE);
        }

	}

    /**
     * 创建支付宝预付单
     * @param detailDOList
     * @param orderDO
     */
	private boolean createAlipayOrder(List<OrderDetailDO> detailDOList,OrderDO  orderDO){

		//查询店铺名称
		ShopDO shopDO = shopService.getByCustomerId(orderDO.getCustomerId());

		OrderAlipayDO alipayDO =  new OrderAlipayDO();
	    alipayDO.setId(orderDO.getId());
	    alipayDO.setBody(StringUtils.join(detailDOList.stream().map(m->m.getOutTitle()).toArray()));
        alipayDO.setGoodsType("1");
        alipayDO.setCreateTime(Calendar.getInstance().getTime());
        alipayDO.setPassbackParams(orderDO.getId().toString());
        alipayDO.setProductCode("QUICK_MSECURITY_PAY");
        //店铺id
        alipayDO.setStoreId(shopDO.getId()+"");
		//店铺名称
        alipayDO.setSubject(shopDO.getName());
        alipayDO.setTimeoutExpress("15m");
        alipayDO.setTotalAmount(orderDO.getOrderTotal().toString());
        alipayDO.setTradeNo(orderDO.getOrderNum());

        String backStr = alipayManager.createTradePay(alipayDO,orderDO.getCustomerId());
        if(backStr==""){
            throw  new  BDException("创建支付宝预付单失败",BDException.VERIFY_ERROR_CODE);
        }
        alipayDO.setCreateBackBody(backStr);

        //保存
        return orderAlipayService.save(alipayDO)>0;
    }

    /**
     * 获取订单号
     * @param
     * @return
     */
	private String getOrderNum(){
		Long time = System.currentTimeMillis();
		return time+""+ (int)Math.random()*1000;
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

	private String dateNum(Integer customerId){
		String redisKey = "date_num_"+customerId+"_"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		long dateNum =redisUtils.incr(redisKey, 1);
		//设置过期时间
		if(dateNum==0){
			redisUtils.expire(redisKey,1, TimeUnit.DAYS);
		}
		return ""+dateNum;
	}

	//endregion

}
