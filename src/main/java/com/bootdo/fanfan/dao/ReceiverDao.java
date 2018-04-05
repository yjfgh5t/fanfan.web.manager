package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.ReceiverDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:17
 */
@Mapper
public interface ReceiverDao {

	ReceiverDO get(Integer id);
	
	List<ReceiverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceiverDO receiver);
	
	int update(ReceiverDO receiver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
