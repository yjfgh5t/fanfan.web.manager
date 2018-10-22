package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.ContactDao;
import com.bootdo.fanfan.domain.ContactDO;
import com.bootdo.fanfan.service.ContactService;



@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao contactDao;
	
	@Override
	public ContactDO get(Integer id){
		return contactDao.get(id);
	}
	
	@Override
	public List<ContactDO> list(Map<String, Object> map){
		return contactDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contactDao.count(map);
	}
	
	@Override
	public int save(ContactDO contact) {
		contact.setCreateTime(new Date());
		return contactDao.save(contact);
	}
	
	@Override
	public int update(ContactDO contact){
		return contactDao.update(contact);
	}
	
	@Override
	public int remove(Integer id){
		return contactDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return contactDao.batchRemove(ids);
	}
	
}
