package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.vo.APIOrderListCustomerVO;
import com.bootdo.fanfan.vo.APIOrderListVO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
public interface OrderService {
	
	OrderDO get(Integer id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 创建订单
	 * @param orderVO
	 */
	Integer createOrder(APIOrderRequVO orderVO);

	/**
	 * 查询当个订单
	 * @param orderId
	 * @return
	 */
	APIOrderRequVO queryOrder(Integer orderId);

	/**
	 * 查询用户订单
	 * @param map
	 * @return
	 */
	List<APIOrderListVO> queryOrderByUser(Map<String,Object> map);

	/**
	 * 查询商户订单
	 * @param map
	 * @return
	 */
	List<APIOrderListCustomerVO> queryOrderByCustomer(Map<String,Object> map);

	/**
	 * 更新订单状态
	 * @param orderDO
	 */
	void updateOrderState(OrderDO orderDO);

	/**
	 * 根据订单号查询 订单 信息
	 * @param orderNum
	 * @return
	 */
	OrderDO queryOrderByOrdernum(String orderNum);

	/**
	 * 查询订单状态
	 * @param idArray
	 * @return
	 */
    List<OrderDO> getStateById(List<Integer> idArray);

	/**
	 * 发送订单通知
	 * @param orderId
	 */
	void sendOrderNotification(Integer orderId);

	/**
	 * 查询商户Id
	 * @param orderId
	 * @return
	 */
	Integer getCustomerIdById(Integer orderId);
}
