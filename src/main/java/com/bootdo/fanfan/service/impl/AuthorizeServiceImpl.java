package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.AuthorizeDao;
import com.bootdo.fanfan.domain.AuthorizeDO;
import com.bootdo.fanfan.service.AuthorizeService;



@Service
public class AuthorizeServiceImpl implements AuthorizeService {
	@Autowired
	private AuthorizeDao authorizeDao;
	
	@Override
	public AuthorizeDO get(Integer id){
		return authorizeDao.get(id);
	}
	
	@Override
	public List<AuthorizeDO> list(Map<String, Object> map){
		return authorizeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return authorizeDao.count(map);
	}
	
	@Override
	public int save(AuthorizeDO authorize){
		return authorizeDao.save(authorize);
	}
	
	@Override
	public int update(AuthorizeDO authorize){
		return authorizeDao.update(authorize);
	}
	
	@Override
	public int remove(Integer id){
		return authorizeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return authorizeDao.batchRemove(ids);
	}
	
}
