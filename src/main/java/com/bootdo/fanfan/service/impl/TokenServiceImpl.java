package com.bootdo.fanfan.service.impl;

import com.bootdo.common.utils.RedisUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.TokenDao;
import com.bootdo.fanfan.domain.TokenDO;
import com.bootdo.fanfan.service.TokenService;



@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public TokenDO get(Integer id){
		return tokenDao.get(id);
	}
	
	@Override
	public List<TokenDO> list(Map<String, Object> map){
		return tokenDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tokenDao.count(map);
	}
	
	@Override
	public int save(TokenDO token){

		Map<String,Object> map = new HashMap<>();
		map.put("customerId",token.getCustomerId());
		map.put("platformType",token.getPlatformType());
		map.put("appId",token.getAppId());
		List<TokenDO> list = list(map);
		if(list.size()>0){
			token.setId(list.get(0).getId());
			return update(token);
		}else {
			return tokenDao.save(token);
		}
	}

	@Override
	public int update(TokenDO token){
		int success =tokenDao.update(token);
		//删除Redis缓存
		if(success>0){
			redisUtils.hdel(RedisConstant.CUSTOMER_TOKEN_KEY,token.getCustomerId()+"_"+token.getPlatformType());
		}
		return success;
	}
	
	@Override
	public int remove(Integer id){
		return tokenDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tokenDao.batchRemove(ids);
	}

	/**
	 * 获取缓存Token
	 * @param customerId
	 * @param platformEnum
	 * @return
	 */
	@Override
	public TokenDO getCacheModel(Integer customerId, PlatformEnum platformEnum){

		TokenDO model = (TokenDO) redisUtils.hget(RedisConstant.CUSTOMER_TOKEN_KEY,customerId+"_"+platformEnum.getVal());

		if(model==null) {
			model = tokenDao.getByCustomerId(customerId, platformEnum.getVal());
			redisUtils.hset(RedisConstant.CUSTOMER_TOKEN_KEY,customerId+"_"+platformEnum.getVal(),model);
		}

		return model;
	}
}
