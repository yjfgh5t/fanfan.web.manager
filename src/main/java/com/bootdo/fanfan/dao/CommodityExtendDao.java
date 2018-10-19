package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.CommodityExtendDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-19 17:00:04
 */
@Mapper
public interface CommodityExtendDao {

	CommodityExtendDO get(Integer id);
	
	List<CommodityExtendDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommodityExtendDO commodityExtend);
	
	int update(CommodityExtendDO commodityExtend);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
