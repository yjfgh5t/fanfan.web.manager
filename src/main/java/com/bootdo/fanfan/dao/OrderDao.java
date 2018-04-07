package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.OrderDO;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.vo.APIOrderListVO;
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
public interface OrderDao {

	OrderDO get(Integer id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select  * from  ff_order  where order_num=${orderNum}")
	OrderDO getByOrderNum(@Param("orderNum") String orderNum);

	@Select("select id from  ff_order  where order_num=${orderNum}")
	Integer getIdByOrderNum(@Param("orderNum") String orderNum);

	/**
	 * 查询订单列表
	 * @param map
	 * @return
	 */
	List<APIOrderListVO> queryOrderByUser(Map<String,Object> map);
}