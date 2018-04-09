package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderAlipayDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:34:51
 */
public interface OrderAlipayService {
	
	OrderAlipayDO get(Integer id);

	String getOrderStrById(Integer  id);
	
	List<OrderAlipayDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderAlipayDO orderAlipay);
	
	int update(OrderAlipayDO orderAlipay);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
