package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.CommoditCategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-06-20 11:12:04
 */
public interface CommoditCategoryService {
	
	CommoditCategoryDO get(Integer id);
	
	List<CommoditCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommoditCategoryDO commoditCategory);
	
	int update(CommoditCategoryDO commoditCategory);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
