package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DeskDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-07-03 12:18:24
 */
@Mapper
public interface DeskDao {

	DeskDO get(Integer id);
	
	List<DeskDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeskDO desk);
	
	int update(DeskDO desk);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
