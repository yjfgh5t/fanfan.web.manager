package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.CommodityDO;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-30 22:53:17
 */
@Mapper
public interface CommodityDao {

	CommodityDO get(Integer id);
	
	List<CommodityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommodityDO commodit);
	
	int update(CommodityDO commodit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select id,commodity_title,commodity_sale_price from ff_commodity where id in(${idArray}) and `status`=1 and `delete`=FALSE")
	List<CommodityDO> queryByIdArray(@Param("idArray") String idArray);

	/**
	 * 修改商品状态
	 * @param status
	 * @return
	 */
	@Update("update ff_commodity set status=${status} where id=${id}")
	int updateState(@Param("id") Integer id, @Param("status") Integer status);

	/**
	 * 查询商品和扩展信息
	 * @param map
	 * @return
	 */
	List<CommodityWidthExtendDO> queryExtends(Map<String,Object> map);

}
