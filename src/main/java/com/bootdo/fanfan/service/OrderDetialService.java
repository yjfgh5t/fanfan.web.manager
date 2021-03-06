package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.OrderDetailDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
public interface OrderDetialService {
	
	OrderDetailDO get(Integer id);
	
	List<OrderDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDetailDO orderDetial);

	/**
	 * 根据订id查询订单详情
	 * @param orderId
	 * @return
	 */
	List<OrderDetailDO> queryByOrderId(Integer orderId);

	/**
	 * 根据订单id查询商品
	 * @param orderIdArry
	 * @return
	 */
	List<OrderDetailDO> queryByOrderIdArray(List<Integer> orderIdArry);

	/**
	 * 批量保存
	 * @param detialDOList
	 * @return
	 */
	void  batchSave(List<OrderDetailDO> detialDOList);
	
	int update(OrderDetailDO orderDetial);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 根据订单id  查询商品图片
	 * @param orderId
	 * @return
	 */
	String queryCommodityImgByOrderId(Integer orderId);
}
