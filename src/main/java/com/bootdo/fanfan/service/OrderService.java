package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.vo.APIOrderListVO;
import com.bootdo.fanfan.vo.APIOrderRequVO;

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

	List<APIOrderListVO> queryOrderByUser(Map<String,Object> map);

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
}
