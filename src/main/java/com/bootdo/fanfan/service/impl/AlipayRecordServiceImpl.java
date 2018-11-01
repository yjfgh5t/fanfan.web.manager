package com.bootdo.fanfan.service.impl;

import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.AlipayRecordDao;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.service.AlipayRecordService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlipayRecordServiceImpl implements AlipayRecordService {
	@Autowired
	private AlipayRecordDao alipayRecordDao;

	@Autowired
	private OrderService orderService;
	
	@Override
	public AlipayRecordDO get(Integer id){
		return alipayRecordDao.get(id);
	}

	@Override
	public AlipayRecordDO getByOutTradeNo(String outTradeNo) {
		return alipayRecordDao.getByOutTradeNo(outTradeNo);
	}

	@Override
	public List<AlipayRecordDO> list(Map<String, Object> map){
		return alipayRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return alipayRecordDao.count(map);
	}
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public int save(AlipayRecordDO alipayRecord){

		//支付宝信息是否保存
		int count = alipayRecordDao.queryByTradeNo(alipayRecord.getTradeNo());

		//判断是否添加 支付宝同步和异步通知时
		if(count==0) {
			OrderDO orderDO = new OrderDO();
			orderDO.setId(Integer.parseInt(alipayRecord.getPassbackParams()));
			orderDO.setOrderState(OrderStateEnum.userPaid.getVal());
			//修改订单状态
			orderService.updateOrderState(orderDO);
			return alipayRecordDao.save(alipayRecord);
		}
		return 1;
	}
	
	@Override
	public int update(AlipayRecordDO alipayRecord){
		return alipayRecordDao.update(alipayRecord);
	}
	
	@Override
	public int remove(Integer id){
		return alipayRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return alipayRecordDao.batchRemove(ids);
	}
	
}
