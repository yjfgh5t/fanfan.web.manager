package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.OrderAlipayDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:34:51
 */
@Mapper
public interface OrderAlipayDao {

	OrderAlipayDO get(Integer id);
	
	List<OrderAlipayDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderAlipayDO orderAlipay);
	
	int update(OrderAlipayDO orderAlipay);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select trade_no from ff_order_alipay where id=#{id}")
	String getOrderStrById(@Param("id") Integer id);
}
