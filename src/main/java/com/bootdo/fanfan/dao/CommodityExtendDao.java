package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.CommodityExtendDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

	/**
	 * 修改客户端删除的规格
	 * @param idArray
	 * @param commodityId
	 * @return
	 */
	@Update("update ff_commodity_extend set `delete`=1 where commodity_id=#{commodityId} and `id` not in(${idArray})")
	int updateDeletes(@Param("idArray") String idArray, @Param("commodityId") Integer commodityId);
}
