package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommoditDao;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;



@Service
public class CommoditServiceImpl implements CommoditService {
	@Autowired
	private CommoditDao commoditDao;
	
	@Override
	public CommoditDO get(Integer id){
		return commoditDao.get(id);
	}
	
	@Override
	public List<CommoditDO> list(Map<String, Object> map){
		return commoditDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commoditDao.count(map);
	}
	
	@Override
	public int save(CommoditDO commodit){
		commodit.setCreateTime(Calendar.getInstance().getTime());
		commodit.setUpdateTime(Calendar.getInstance().getTime());
		return commoditDao.save(commodit);
	}
	
	@Override
	public int update(CommoditDO commodit){
		return commoditDao.update(commodit);
	}
	
	@Override
	public int remove(Integer id){
		return commoditDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return commoditDao.batchRemove(ids);
	}
	
}
