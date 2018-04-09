package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.OrderAlipayDao;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import com.bootdo.fanfan.service.OrderAlipayService;



@Service
public class OrderAlipayServiceImpl implements OrderAlipayService {
	@Autowired
	private OrderAlipayDao orderAlipayDao;
	
	@Override
	public OrderAlipayDO get(Integer id){
		return orderAlipayDao.get(id);
	}

	public String getOrderStrById(Integer  id){
		return orderAlipayDao.getOrderStrById(id);
	}

	@Override
	public List<OrderAlipayDO> list(Map<String, Object> map){
		return orderAlipayDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderAlipayDao.count(map);
	}
	
	@Override
	public int save(OrderAlipayDO orderAlipay){
		return orderAlipayDao.save(orderAlipay);
	}
	
	@Override
	public int update(OrderAlipayDO orderAlipay){
		return orderAlipayDao.update(orderAlipay);
	}
	
	@Override
	public int remove(Integer id){
		return orderAlipayDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderAlipayDao.batchRemove(ids);
	}
	
}
