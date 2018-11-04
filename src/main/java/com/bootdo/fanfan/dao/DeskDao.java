package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DTO.DeskDTO;
import com.bootdo.fanfan.domain.DeskDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-07-03 12:18:24
 */
@Mapper
public interface DeskDao {

	DeskDO get(Integer id);
	
	List<DeskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeskDO desk);
	
	int update(DeskDO desk);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select fd.*,fq.id as qr_code_id from ff_desk as fd INNER JOIN ff_qrcode fq on fd.id = fq.desk_id where fd.customer_id=#{customerId} order by fd.id")
	List<DeskDTO> queryList(@Param("customerId") Integer customerId);
}
