package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.AlipayKeyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-13 15:48:27
 */
@Mapper
public interface AlipayKeyDao {

	AlipayKeyDO get(Integer id);
	
	List<AlipayKeyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlipayKeyDO alipayKey);
	
	int update(AlipayKeyDO alipayKey);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select * from ff_alipay_key where customer_id = #{customerId}")
	AlipayKeyDO getByCustomerId(@Param("customerId") Integer customerId);
}
