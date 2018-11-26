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

	@Select("SELECT fi.id,fi.form_id as formId,tp.tp_id as tpId from ff_form_id as fi join ff_tp_user as tp on tp.user_id = fi.user_id  where fi.expired_time>NOW() and fi.use_count<=#{count} and fi.user_id=#{userId} and fi.form_type=#{type} and tp.tp_type=#{type} limit 0,1")
	FormUserDTO getCanUseFormId(@Param("type") Integer type, @Param("count") Integer count, @Param("userId") Integer userId);

	@Update("UPDATE ff_form_id set use_count = user_count+1 where id=#{id}")
	int addFormIdUseOnce(@Param("id") Integer id);
}
