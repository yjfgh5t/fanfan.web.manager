package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.OrderReceiverDao;
import com.bootdo.fanfan.domain.OrderReceiverDO;
import com.bootdo.fanfan.service.OrderReceiverService;



@Service
public class OrderReceiverServiceImpl implements OrderReceiverService {
	@Autowired
	private OrderReceiverDao orderReceiverDao;
	
	@Override
	public OrderReceiverDO get(Integer id){
		return orderReceiverDao.get(id);
	}
	
	@Override
	public List<OrderReceiverDO> list(Map<String, Object> map){
		return orderReceiverDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderReceiverDao.count(map);
	}
	
	@Override
	public int save(OrderReceiverDO orderReceiver) {
		if (orderReceiverDao.hasSave(orderReceiver.getId())==0)
			return orderReceiverDao.save(orderReceiver);
		else
			return orderReceiverDao.update(orderReceiver);
	}

	@Override
	public int update(OrderReceiverDO orderReceiver){
		return orderReceiverDao.update(orderReceiver);
	}
	
	@Override
	public int remove(Integer id){
		return orderReceiverDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderReceiverDao.batchRemove(ids);
	}

	public OrderReceiverDO queryById(Integer id){
		return orderReceiverDao.queryById(id);
	}
}
