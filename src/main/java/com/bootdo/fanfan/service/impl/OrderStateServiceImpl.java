package com.bootdo.fanfan.service.impl;

import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.fanfan.dao.OrderStateDao;
import com.bootdo.fanfan.domain.OrderStateDO;
import com.bootdo.fanfan.service.OrderStateService;

import javax.annotation.PostConstruct;


@Service
public class OrderStateServiceImpl implements OrderStateService {
	@Autowired
	private OrderStateDao orderStateDao;

	/**
	 * 待支付的订单集合
	 */
	public Map<Integer,Date> awaitPayOrders = new HashMap<>();

	/**
	 * 初始加载
	 */
	@PostConstruct
	private void init(){
		//加载待支付的订单
		List<OrderStateDO> listOrder= orderStateDao.queryAwaitPayOrder(OrderStateEnum.userWaitPay.getVal());
		if(listOrder==null) {
			listOrder.forEach((item)->{
				awaitPayOrders.put(item.getOrderId(),item.getCreateTime());
			});
		}
	}

	@Override
	public OrderStateDO get(Integer id){
		return orderStateDao.get(id);
	}
	
	@Override
	public List<OrderStateDO> list(Map<String, Object> map){
		return orderStateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderStateDao.count(map);
	}
	
	@Override
	public int save(OrderStateDO orderState) {
		//查询此订单状态是否存在
		Integer id =  orderStateDao.queryHasSave(orderState.getOrderId(), orderState.getOrderState());

		if (id != null) {
			throw  new SecurityException("订单状态已存在不可重复添加");
		}

		//设置当前时间
		orderState.setCreateTime(Calendar.getInstance().getTime());

		int result = orderStateDao.save(orderState);

		//判断是否待支付订单
		addAwaitPayOrder(orderState);

		return result;
	}

	
	@Override
	public int remove(Integer id){
		return orderStateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderStateDao.batchRemove(ids);
	}

	/**
	 * 查询订单某状态下的时间
	 * @param orderId
	 * @param orderState
	 * @return
	 */
	@Override
	public Date queryStateDate(Integer orderId, Integer orderState) {
		return orderStateDao.queryStateDate(orderId,orderState);
	}

	@Override
	public Map<Integer, Date> getAwaitPayOrder() {
		return awaitPayOrders;
	}


	/**
	 * 添加待支付的订单至监控集合
	 * @param orderState
	 */
	private void addAwaitPayOrder(OrderStateDO orderState){

		//更改的订单状态在监控集合中 切不为待支付状态
		if(awaitPayOrders.containsKey(orderState.getOrderId()) && orderState.getOrderState()> OrderStateEnum.userWaitPay.getVal() ){
			awaitPayOrders.remove(orderState.getOrderId());
			return;
		}

		//判断是否待支付订单
		if(orderState.getOrderState()== OrderStateEnum.userWaitPay.getVal()){
			awaitPayOrders.put(orderState.getOrderId(),orderState.getCreateTime());
		}

	}

}
