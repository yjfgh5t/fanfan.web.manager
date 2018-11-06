package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.AuthorizeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-06 23:51:38
 */
public interface AuthorizeService {
	
	AuthorizeDO get(Integer id);
	
	List<AuthorizeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AuthorizeDO authorize);
	
	int update(AuthorizeDO authorize);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
