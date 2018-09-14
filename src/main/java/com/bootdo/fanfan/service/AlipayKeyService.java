package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.AlipayKeyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-13 15:33:17
 */
public interface AlipayKeyService {
	
	AlipayKeyDO get(Integer id);

	AlipayKeyDO getByCustomerId(Integer customerId);
	
	List<AlipayKeyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlipayKeyDO alipayKey);
	
	int update(AlipayKeyDO alipayKey);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
