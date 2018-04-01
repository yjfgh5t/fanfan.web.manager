package com.bootdo.fanfan.service.impl;

import com.bootdo.fanfan.dao.FFUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.service.UserService;



@Service("FFUserServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private FFUserDao ffUserDao;
	
	@Override
	public UserDO get(Integer id){
		return ffUserDao.get(id);
	}

	@Override
	public List<UserDO> list(Map<String, Object> map){
		return ffUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ffUserDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		if(user==null)
			return 0;

		if(user.getId()==null) {
			user.setDelete(0);
			user.setUserState(1);
			user.setCreateTime(Calendar.getInstance().getTime());
			return ffUserDao.save(user);
		}
 		else{
 			return update(user)>0?user.getId():0;
		}
	}
	
	@Override
	public int update(UserDO user){
		return ffUserDao.update(user);
	}
	
	@Override
	public int remove(Integer id){
		return ffUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return ffUserDao.batchRemove(ids);
	}

}
