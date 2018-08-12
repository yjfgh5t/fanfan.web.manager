package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.FormIdDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-10 17:02:40
 */
public interface FormIdService {

	String getCanUseFormId(PlatformEnum platformEnum, Integer userId);

	FormIdDO get(Integer id);
	
	List<FormIdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FormIdDO formId);
	
	int update(FormIdDO formId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
