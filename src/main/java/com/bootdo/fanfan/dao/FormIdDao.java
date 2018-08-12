package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.FormIdDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-10 17:02:40
 */
@Mapper
public interface FormIdDao {

	FormIdDO get(Integer id);
	
	List<FormIdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FormIdDO formId);
	
	int update(FormIdDO formId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("SELECT form_id from ff_form_id where expired_time>NOW() and use_count<#{count} and user_id=#{userId} and form_type=#{type} limit 0,1")
	String getCanUseFormId(@Param("type") Integer type, @Param("count") Integer count, @Param("userId") Integer userId);
}
