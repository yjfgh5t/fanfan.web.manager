package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.CommoditDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-20 16:13:33
 */
public interface CommoditService {
	
	CommoditDO get(Integer id);
	
	List<CommoditDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommoditDO commodit);
	
	int update(CommoditDO commodit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
