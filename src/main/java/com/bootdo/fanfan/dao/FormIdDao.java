package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DTO.FormUserDTO;
import com.bootdo.fanfan.domain.FormIdDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	@Select("SELECT id,form_id ,tp_id,form_type from ff_form_id  where expired_time>NOW() and use_count>0 and user_id=#{userId} limit 0,1")
	FormUserDTO getCanUseFormId(@Param("userId") Integer userId);

	@Update("UPDATE ff_form_id set use_count = user_count-1 where id=#{id}")
	int addFormIdUseOnce(@Param("id") Integer id);
}
