package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-30 22:53:17
 */
public interface CommoditService {
	
	CommoditDO get(Integer id);
	
	List<CommoditDO> list(Map<String, Object> map);

	List<CommodityWidthExtendDO> listExtend(Map<String,Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommoditDO commodit);
	
	int update(CommoditDO commodit);
	
	int remove(Integer id);

	/**
	 * 商品上架 下架
	 * @param id 商品id
	 *  @param pullOff 下架
	 * @return
	 */
	int updateStatus(Integer id,boolean pullOff);
	
	int batchRemove(Integer[] ids);

	/**
	 * 根据Id集合查询商品
	 * @param idArry
	 * @return
	 */
	List<CommoditDO> queryByIdarry(List<Integer> idArry);
}
