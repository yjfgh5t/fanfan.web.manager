package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.AlipayRecordDao;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.service.AlipayRecordService;



@Service
public class AlipayRecordServiceImpl implements AlipayRecordService {
	@Autowired
	private AlipayRecordDao alipayRecordDao;
	
	@Override
	public AlipayRecordDO get(Integer id){
		return alipayRecordDao.get(id);
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
	public int save(AlipayRecordDO alipayRecord){
		return alipayRecordDao.save(alipayRecord);
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
