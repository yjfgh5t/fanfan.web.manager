package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.TpUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:50:47
 */
@Mapper
public interface TpUserDao {

	TpUserDO get(Integer id);
	
	List<TpUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TpUserDO tpUser);
	
	int update(TpUserDO tpUser);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select tp_nick,user_id,tp_icon,tp_sex from ff_tp_user where tp_id=#{tpId}")
	TpUserDO getByTpId(@Param("tpId") String tpId);

	/**
	 * 获取第三方用户Id
	 * @param userId
	 * @param appId
	 * @return
	 */
	@Select("select tp_id from ff_tp_user where user_id=#{userId} and tp_app_id=${appId}")
	String getTpId(@Param("userId") Integer userId,@Param("appId") String appId);

	@Select("select tp_nick,user_id,tp_icon,tp_sex from ff_tp_user where user_id=#{userId}")
	TpUserDO getByUserId(@Param("userId") Integer userId);
}
