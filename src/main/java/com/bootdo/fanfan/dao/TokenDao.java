package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.TokenDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-05 14:23:08
 */
@Mapper
public interface TokenDao {

	TokenDO get(Integer id);
	
	List<TokenDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TokenDO token);
	
	int update(TokenDO token);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select * from ff_token where customer_id=#{customerId} and platform_type=#{platformType} limit 1")
	TokenDO getByCustomerId(@Param("customerId") Integer customerId,@Param("platformType") Integer platformType);
}
