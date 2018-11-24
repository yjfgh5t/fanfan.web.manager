package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.ShopDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-12 17:10:19
 */
public interface ShopService {
	
	ShopDO get(Integer id);

	ShopDO getByCustomerId(Integer customerId);

	/**
	 * 获取店铺名称
	 * @param customerId
	 * @return
	 */
	String getNameByCustomerId(Integer customerId);
	
	List<ShopDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ShopDO shop);
	
	int update(ShopDO shop);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
