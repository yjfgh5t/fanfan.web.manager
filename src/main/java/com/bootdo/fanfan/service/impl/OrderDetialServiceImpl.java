package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.OrderDetialDao;
import com.bootdo.fanfan.domain.OrderDetialDO;
import com.bootdo.fanfan.service.OrderDetialService;



@Service
public class OrderDetialServiceImpl implements OrderDetialService {
	@Autowired
	private OrderDetialDao orderDetialDao;
	
	@Override
	public OrderDetialDO get(Integer id){
		return orderDetialDao.get(id);
	}
	
	@Override
	public List<OrderDetialDO> list(Map<String, Object> map){
		return orderDetialDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDetialDao.count(map);
	}
	
	@Override
	public int save(OrderDetialDO orderDetial) {
			return orderDetialDao.save(orderDetial);
	}

	@Override
	public List<OrderDetialDO> queryByOrderId(Integer orderId){
		return orderDetialDao.queryByOrderId(orderId);
	}

	/**
	 * 批量查询订单详情
	 * @param orderIdArray
	 * @return
	 */
	@Override
	public List<OrderDetialDO> queryByOrderIdArray(List<Integer> orderIdArray) {
		return orderDetialDao.queryByOrderIdArray(orderIdArray);
	}

	@Override
	public void batchSave(List<OrderDetialDO> detialDOList) {
		for (OrderDetialDO orderDetialDO  :  detialDOList){
			Integer id =null;
			//判断改商品是否已经添加
			if(orderDetialDO.getOrderId()!=null) {
				id = orderDetialDao.queryHasSave(orderDetialDO.getOrderId(), orderDetialDO.getOutId());
			}
			//判断是否存在
			if(id==null)
				save(orderDetialDO);
		}
	}

	@Override
	public int update(OrderDetialDO orderDetial){
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
