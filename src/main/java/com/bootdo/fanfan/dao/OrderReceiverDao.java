package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.OrderReceiverDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:17
 */
@Mapper
public interface OrderReceiverDao {

	OrderReceiverDO get(Integer id);
	
	List<OrderReceiverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderReceiverDO orderReceiver);
	
	int update(OrderReceiverDO orderReceiver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select count(id) from ff_order_receiver where id=#{id}")
	Integer hasSave(@Param("id") Integer id);

	@Select("select * from ff_order_receiver where id=#{id}")
	OrderReceiverDO queryById(@Param("id") Integer id);
}
