package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.CommodityDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-30 22:53:17
 */
public interface CommodityService {
	
	CommodityDO get(Integer id);
	
	List<CommodityDO> list(Map<String, Object> map);

	CommodityWidthExtendDO getExtend(Integer id);

	List<CommodityWidthExtendDO> listExtend(Map<String,Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CommodityDO commodit);
	
	int update(CommodityDO commodit);
	
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
	 * @param idArray
	 * @return
	 */
	List<CommodityWidthExtendDO> queryByIdArray(List<Integer> idArray);

	/**
	 * 设置推荐商品
	 * @param commodityIds
	 * @param customerId
	 * @return
	 */
	int setRecommend(Integer [] commodityIds,Integer customerId);

	/**
	 * 查询推荐商品
	 * @param customerId
	 * @return
	 */
	List<CommodityDO> getRecommend(Integer customerId);
}
