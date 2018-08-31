package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.DTO.DeskDTO;
import com.bootdo.fanfan.domain.DeskDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-07-03 12:18:24
 */
public interface DeskService {
	
	DeskDO get(Integer id);
	
	List<DeskDO> list(Map<String, Object> map);

	/**
	 * 查询列表
	 * @param customerId
	 * @return
	 */
	List<DeskDTO> queryList(Integer customerId);
	
	int count(Map<String, Object> map);
	
	int save(DeskDO desk);
	
	int update(DeskDO desk);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 获取二维码路径
	 * @param id
	 * @return
	 */
	String getQRCode(Integer id);
}
