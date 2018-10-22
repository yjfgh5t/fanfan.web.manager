package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.ContactDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-22 11:16:13
 */
public interface ContactService {
	
	ContactDO get(Integer id);
	
	List<ContactDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContactDO contact);
	
	int update(ContactDO contact);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
