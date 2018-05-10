package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderStateDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
public interface OrderStateService {
	
	OrderStateDO get(Integer id);
	
	List<OrderStateDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderStateDO orderState);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 查询订单某状态下的时间
	 * @param orderId
	 * @param orderState
	 * @return
	 */
	Date queryStateDate(Integer orderId,Integer orderState);

	/**
	 * 获取超时未支付的订单
	 * @return
	 */
	Map<Integer,Date> getAwaitPayOrder();
}
