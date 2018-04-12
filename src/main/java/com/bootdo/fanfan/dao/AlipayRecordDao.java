package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.AlipayRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-11 15:33:59
 */
@Mapper
public interface AlipayRecordDao {

	AlipayRecordDO get(Integer id);
	
	List<AlipayRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlipayRecordDO alipayRecord);
	
	int update(AlipayRecordDO alipayRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
