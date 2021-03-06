package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DTO.OrderStatisticsDTO;
import com.bootdo.fanfan.domain.OrderDO;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.vo.APIOrderListCustomerVO;
import com.bootdo.fanfan.vo.APIOrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Select("select  * from  ff_order  where order_num=#{createOrderNum}")
	OrderDO getByOrderNum(@Param("createOrderNum") String orderNum);

	@Select("select id,order_state from ff_order where order_num=#{createOrderNum}")
	OrderDO getIdByOrderNum(@Param("createOrderNum") String orderNum);

	/**
	 * 查询商户id
	 * @param id
	 * @return
	 */
	@Select("select customer_id from  ff_order  where id=#{id}")
	Integer getCustomerIdById(@Param("id") Integer id);

	/**
	 * 查询退款信息
	 * @param id
	 * @return
	 */
	@Select("select order_num,customer_id,order_pay,order_pay_type from  ff_order  where id=#{id}")
	OrderDO getOrderTrande(@Param("id") Integer id);

	/**
	 * 查询订单状态
	 * @param idArray
	 * @return
	 */
	List<OrderDO> getStateById(@Param("idArray") List<Integer> idArray);

	/**
	 * 更新订单状态
	 * @param orderState
	 * @param id
	 * @return
	 */
	@Update("update ff_order set order_state=#{orderState} where id=#{id}")
	int updateOrderState(@Param("orderState") Integer orderState, @Param("id") Integer id);

	/**
	 * 更新订单状态和文本
	 * @param orderState
	 * @param id
	 * @return
	 */
	@Update("update ff_order set order_state=#{orderState}, order_customer_remark=#{customerRemark} where id=#{id}")
	int updateOrderStateCancel(@Param("orderState") Integer orderState, @Param("id") Integer id, @Param("customerRemark") String customerRemark);

	/**
	 * 更新订单支付状态
	 * @param orderState
	 * @param id
	 * @return
	 */
	@Update("update ff_order set order_state=#{orderState}, order_date_num=#{orderDateNum}, order_time = NOW() where id=#{id}")
	int updateOrderStatePay(@Param("orderState") Integer orderState, @Param("id") Integer id, @Param("orderDateNum") String dateNum);

	@Select("select customer_id from ff_order where id = #{id}")
	Integer queryCustomerIdById(@Param("id") Integer id);

	/**
	 * 查询订单列表-用户
	 * @param map
	 * @return
	 */
	List<APIOrderListVO> queryOrderByUser(Map<String,Object> map);

	/**
	 * 查询订单列表-商家
	 * 未支付的订单不会查出来 只有已支付的订单才会有order_time值
	 * @param map
	 * @return
	 */
	List<APIOrderListCustomerVO> queryOrderByCustomer(Map<String,Object> map);

	/**
	 * 查询订单统计信息
	 * @param map
	 */
	OrderStatisticsDTO queryOrderStatistics(Map<String,Object> map);
}
