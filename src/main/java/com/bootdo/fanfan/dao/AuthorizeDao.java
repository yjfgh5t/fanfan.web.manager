package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.AuthorizeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-06 23:51:38
 */
@Mapper
public interface AuthorizeDao {

	AuthorizeDO get(Integer id);
	
	List<AuthorizeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AuthorizeDO authorize);
	
	int update(AuthorizeDO authorize);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select authorize_state,identification_state from ff_authorize where id =#{id}")
	AuthorizeDO getState(@Param("id") Integer id);

	@Select("select id from ff_authorize where customer_id = #{customerId}")
	Integer getId(@Param("customerId") Integer customerId);

	@Select("select * from ff_authorize where customer_id = #{customerId}")
	AuthorizeDO getByCustomerId(@Param("customerId") Integer customerId);
}
