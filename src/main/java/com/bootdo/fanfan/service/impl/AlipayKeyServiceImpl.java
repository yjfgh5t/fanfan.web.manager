package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.AlipayKeyDao;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.service.AlipayKeyService;

@Service
public class AlipayKeyServiceImpl implements AlipayKeyService {
	@Autowired
	private AlipayKeyDao alipayKeyDao;
	
	@Override
	public AlipayKeyDO get(Integer id){
		return alipayKeyDao.get(id);
	}

	@Override
	public AlipayKeyDO getByCustomerId(Integer customerId) {
		return alipayKeyDao.getByCustomerId(customerId);
	}

	@Override
	public List<AlipayKeyDO> list(Map<String, Object> map){
		return alipayKeyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return alipayKeyDao.count(map);
	}
	
	@Override
	public int save(AlipayKeyDO alipayKey){
		alipayKey.setCreateTime(new Date());
		return alipayKeyDao.save(alipayKey);
	}
	
	@Override
	public int update(AlipayKeyDO alipayKey){
		return alipayKeyDao.update(alipayKey);
	}
	
	@Override
	public int remove(Integer id){
		return alipayKeyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return alipayKeyDao.batchRemove(ids);
	}

	@Override
	public AlipayKeyDO getByAppId(String appId) {
		return alipayKeyDao.getByAppId(appId);
	}

}
