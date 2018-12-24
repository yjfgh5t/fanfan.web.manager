package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.DeliveryDao;
import com.bootdo.fanfan.domain.DeliveryDO;
import com.bootdo.fanfan.service.DeliveryService;



@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private DeliveryDao deliveryDao;
	
	@Override
	public DeliveryDO get(Integer id){
		return deliveryDao.get(id);
	}
	
	@Override
	public List<DeliveryDO> list(Map<String, Object> map){
		return deliveryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deliveryDao.count(map);
	}
	
	@Override
	public int save(DeliveryDO delivery){
		delivery.setDelete(0);
		return deliveryDao.save(delivery);
	}
	
	@Override
	public int update(DeliveryDO delivery){
		return deliveryDao.update(delivery);
	}

	@Override
	public List<DeliveryDO> getByCustomerId(Integer customerId){

		Map<String,Object> params = new HashMap<>();
		params.put("delete","0");
		params.put("customerId",customerId);
		params.put("sort","id");
		params.put("order","asc");

		return list(params);
	}

	/**
	 * 默认
	 * @param customerId
	 * @return
	 */
	@Override
	public DeliveryDO getDefaultByCustomerId(Integer customerId) {
		Map<String, Object> params = new HashMap<>();
		params.put("delete", "0");
		params.put("customerId", customerId);
		params.put("is_default", 1);

		List<DeliveryDO> list = list(params);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public int remove(Integer id){
		DeliveryDO delivery = new DeliveryDO();
		delivery.setId(id);
		delivery.setDelete(1);
		return deliveryDao.update(delivery);
	}

	@Override
	public int setDefault(Integer id) {
		return deliveryDao.setDefault(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return deliveryDao.batchRemove(ids);
	}
	
}
