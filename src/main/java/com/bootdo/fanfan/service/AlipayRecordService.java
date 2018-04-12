package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.AlipayRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-11 15:33:59
 */
public interface AlipayRecordService {
	
	AlipayRecordDO get(Integer id);
	
	List<AlipayRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlipayRecordDO alipayRecord);
	
	int update(AlipayRecordDO alipayRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
