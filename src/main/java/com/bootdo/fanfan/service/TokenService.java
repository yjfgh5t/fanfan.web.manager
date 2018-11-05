package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.TokenDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-05 14:23:08
 */
public interface TokenService {
	
	TokenDO get(Integer id);
	
	List<TokenDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TokenDO token);
	
	int update(TokenDO token);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	TokenDO getCacheModel(Integer customerId, PlatformEnum platformEnum);
}
