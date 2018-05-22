package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.DeliveryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-22 22:03:11
 */
public interface DeliveryService {
	
	DeliveryDO get(Integer id);
	
	List<DeliveryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeliveryDO delivery);
	
	int update(DeliveryDO delivery);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
