package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
	public AuthorizeDO getByCustomerId(Integer customerId) {
		return authorizeDao.getByCustomerId(customerId);
	}

	@Override
	public	AuthorizeDO getState(Integer id){
		return authorizeDao.getState(id);
	}
	@Override
	public AuthorizeDO getStateByCustomerId(Integer customerId){
		return authorizeDao.getStateByCustomerId(customerId);
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

		if(null == authorize.getId()){
			//查询改商户是否授权
			Integer id = authorizeDao.getId(authorize.getCustomerId());
			if(null != id){
				authorize.setId(id);
			}
		}

		int success = -1;

		if(null == authorize.getId()){
			authorize.setCreateTime(new Date());
			success = authorizeDao.save(authorize);
		}else {
			authorize.setModifyTime(new Date());
			success = update(authorize);
		}

		return success;
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
