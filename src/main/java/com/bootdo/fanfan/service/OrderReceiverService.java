package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderReceiverDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:17
 */
public interface OrderReceiverService {
	
	OrderReceiverDO get(Integer id);
	
	List<OrderReceiverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderReceiverDO orderReceiver);
	
	int update(OrderReceiverDO orderReceiver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
