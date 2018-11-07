package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.DictionaryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-15 21:29:01
 */
public interface DictionaryService {
	
	DictionaryDO get(Integer id);
	
	List<DictionaryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictionaryDO dictionary);
	
	int update(DictionaryDO dictionary);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 查询多个key
	 * @param keys
	 * @return
	 */
	Map<Integer,String> queryByKeys(Integer  ...keys);

}
