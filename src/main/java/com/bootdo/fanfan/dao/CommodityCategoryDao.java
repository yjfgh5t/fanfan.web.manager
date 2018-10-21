package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.CommodityCategoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-06-20 11:12:04
 */
@Mapper
public interface CommodityCategoryDao {

	CommodityCategoryDO get(Integer id);
	
	List<CommodityCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommodityCategoryDO commodityCategory);
	
	int update(CommodityCategoryDO commodityCategory);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
