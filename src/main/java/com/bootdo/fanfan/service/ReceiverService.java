package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.ReceiverDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:17
 */
public interface ReceiverService {
	
	ReceiverDO get(Integer id);
	
	List<ReceiverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceiverDO receiver);
	
	int update(ReceiverDO receiver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
