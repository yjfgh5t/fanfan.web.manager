package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.DeskDao;
import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.service.DeskService;



@Service
public class DeskServiceImpl implements DeskService {
	@Autowired
	private DeskDao deskDao;
	
	@Override
	public DeskDO get(Integer id){
		return deskDao.get(id);
	}
	
	@Override
	public List<DeskDO> list(Map<String, Object> map){
		return deskDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deskDao.count(map);
	}
	
	@Override
	public int save(DeskDO desk){
		return deskDao.save(desk);
	}
	
	@Override
	public int update(DeskDO desk){
		return deskDao.update(desk);
	}
	
	@Override
	public int remove(Integer id){
		return deskDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deskDao.batchRemove(ids);
	}
	
}
