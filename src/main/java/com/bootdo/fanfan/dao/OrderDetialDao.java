package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.OrderDetailDO;

import java.util.List;
import java.util.Map;

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
public interface OrderDetialDao {

	OrderDetailDO get(Integer id);
	
	List<OrderDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDetailDO orderDetail);
	
	int update(OrderDetailDO orderDetail);
	
	int remove(Integer id);

	@Select("select id from ff_order_detail where order_id=#{orderId} and out_id=#{outId}")
	Integer queryHasSave(@Param("orderId") Integer orderId ,@Param("outId") Integer outId);

	@Select("select * from ff_order_detail where order_id=#{orderId}")
	List<OrderDetailDO> queryByOrderId(@Param("orderId") Integer orderId);

	/**
	 * 批量查询订单商品
	 * @param orderIdArray
	 * @return
	 */
	List<OrderDetailDO> queryByOrderIdArray(@Param("orderIdArray") List<Integer> orderIdArray);

	int batchRemove(Integer[] ids);

	/**
	 * 根据订单id  查询商品图片
	 * @param orderId
	 * @return
	 */
	String queryCommodityImgByOrderId(@Param("orderId") Integer orderId);
}
