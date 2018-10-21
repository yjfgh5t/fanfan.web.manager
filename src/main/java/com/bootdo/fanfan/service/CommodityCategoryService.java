package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.CommodityCategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-06-20 11:12:04
 */
public interface CommodityCategoryService {
	
	CommodityCategoryDO get(Integer id);
	
	List<CommodityCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommodityCategoryDO commoditCategory);
	
	int update(CommodityCategoryDO commoditCategory);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
