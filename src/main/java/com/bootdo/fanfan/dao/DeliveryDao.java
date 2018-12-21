package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DeliveryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-22 22:03:11
 */
@Mapper
public interface DeliveryDao {

	DeliveryDO get(Integer id);
	
	List<DeliveryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeliveryDO delivery);
	
	int update(DeliveryDO delivery);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Update("update ff_delivery set is_default = (case when `id` = ${id} then 1 else 0 end) where is_default=1 or id=${id}")
	int setDefault(@Param("id")Integer id);
}
