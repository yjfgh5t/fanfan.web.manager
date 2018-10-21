package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.OrderDetialDao;
import com.bootdo.fanfan.domain.OrderDetailDO;
import com.bootdo.fanfan.service.OrderDetialService;



@Service
public class OrderDetailServiceImpl implements OrderDetialService {
	@Autowired
	private OrderDetialDao orderDetialDao;
	
	@Override
	public OrderDetailDO get(Integer id){
		return orderDetialDao.get(id);
	}
	
	@Override
	public List<OrderDetailDO> list(Map<String, Object> map){
		return orderDetialDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDetialDao.count(map);
	}
	
	@Override
	public int save(OrderDetailDO orderDetial) {
			return orderDetialDao.save(orderDetial);
	}

	@Override
	public List<OrderDetailDO> queryByOrderId(Integer orderId){
		return orderDetialDao.queryByOrderId(orderId);
	}

	/**
	 * 批量查询订单详情
	 * @param orderIdArray
	 * @return
	 */
	@Override
	public List<OrderDetailDO> queryByOrderIdArray(List<Integer> orderIdArray) {
		return orderDetialDao.queryByOrderIdArray(orderIdArray);
	}

	@Override
	public void batchSave(List<OrderDetailDO> detialDOList) {
		for (OrderDetailDO orderDetailDO :  detialDOList){
			Integer id =null;
			//判断改商品是否已经添加
			if(orderDetailDO.getOrderId()!=null) {
				id = orderDetialDao.queryHasSave(orderDetailDO.getOrderId(), orderDetailDO.getOutId());
			}
			//判断是否存在
			if(id==null)
				save(orderDetailDO);
		}
	}

	@Override
	public int update(OrderDetailDO orderDetial){
		return orderDetialDao.update(orderDetial);
	}
	
	@Override
	public int remove(Integer id){
		return orderDetialDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderDetialDao.batchRemove(ids);
	}

	@Override
	public String queryCommodityImgByOrderId(Integer orderId) {
		return orderDetialDao.queryCommodityImgByOrderId(orderId);
	}

}
