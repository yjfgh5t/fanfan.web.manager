package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DTO.OrderRefreshDTO;
import com.bootdo.fanfan.domain.OrderStateDO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
@Mapper
public interface OrderStateDao {

	OrderStateDO get(Integer id);
	
	List<OrderStateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderStateDO orderState);
	
	int update(OrderStateDO orderState);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("SELECT id from ff_order_state where order_id =${orderId} and order_state=${orderState}")
	Integer queryHasSave(@Param("orderId") Integer orderId,@Param("orderState") Integer orderState);

	@Select("SELECT create_time from ff_order_state where order_id =${orderId} and order_state=${orderState}")
	Date queryStateDate(@Param("orderId") Integer orderId,@Param("orderState") Integer orderState);

	/**
	 * 查询超时 待支付的订单
	 * @return
	 */
	@Select("select os.order_id,os.create_time,o.order_num from ff_order_state os join ff_order o on os.order_id = o.id where os.order_state=#{orderState} and o.order_state=#{orderState}")
	List<OrderRefreshDTO> queryAwaitPayOrder(@Param("orderState") Integer orderState);
}
