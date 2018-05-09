package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.OrderStateDao;
import com.bootdo.fanfan.domain.OrderStateDO;
import com.bootdo.fanfan.service.OrderStateService;



@Service
public class OrderStateServiceImpl implements OrderStateService {
	@Autowired
	private OrderStateDao orderStateDao;
	
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
		Integer id =null;
		if(orderState.getOrderId()!=null) {
			id = orderStateDao.queryHasSave(orderState.getOrderId(), orderState.getOrderState());
		}
		if (id == null) {
			//设置当前时间
			orderState.setCreateTime(Calendar.getInstance().getTime());
			return orderStateDao.save(orderState);
		}
		return id;
	}



	@Override
	public int update(OrderStateDO orderState){
		return orderStateDao.update(orderState);
	}
	
	@Override
	public int remove(Integer id){
		return orderStateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderStateDao.batchRemove(ids);
	}

	@Override
	public Date queryStateDate(Integer orderId, Integer orderState) {
		return orderStateDao.queryStateDate(orderId,orderState);
	}

}
