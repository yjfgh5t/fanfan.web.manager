package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.TpUserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:50:47
 */
public interface TpUserService {
	
	TpUserDO get(Integer id);

	/**
	 * 获取第三方用户信息
	 * @param code 令牌
	 * @param type  类型 1：支付宝 2：微信
	 * @return
	 */
	TpUserDO getTPInfo(String code,int type,Integer customerId);
	
	List<TpUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TpUserDO tpUser);
	
	int update(TpUserDO tpUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
