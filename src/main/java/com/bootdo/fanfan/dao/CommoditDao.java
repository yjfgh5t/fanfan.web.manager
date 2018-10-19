package com.bootdo.fanfan.dao;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.CommoditDO;

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
public interface CommoditDao {

	CommoditDO get(Integer id);
	
	List<CommoditDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommoditDO commodit);
	
	int update(CommoditDO commodit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select id,commodit_title,commodit_sale_price from ff_commodit where id in(${idArray}) and `status`=1 and `delete`=FALSE")
	List<CommoditDO> queryByIdArry(@Param("idArry") String idArray);

	/**
	 * 修改商品状态
	 * @param status
	 * @return
	 */
	@Update("update ff_commodit set status=${status} where id=${id}")
	int updateState(@Param("id") Integer id, @Param("status") Integer status);

	/**
	 * 查询商品和扩展信息
	 * @param map
	 * @return
	 */
	List<CommodityWidthExtendDO> queryExtends(Map<String,Object> map);

}
