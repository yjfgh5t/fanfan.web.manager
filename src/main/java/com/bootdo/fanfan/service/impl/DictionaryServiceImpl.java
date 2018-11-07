package com.bootdo.fanfan.service.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.DictionaryDao;
import com.bootdo.fanfan.domain.DictionaryDO;
import com.bootdo.fanfan.service.DictionaryService;



@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;

	//region 生成方法
	@Override
	public DictionaryDO get(Integer id){
		return dictionaryDao.get(id);
	}
	
	@Override
	public List<DictionaryDO> list(Map<String, Object> map){
		return dictionaryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dictionaryDao.count(map);
	}
	
	@Override
	public int save(DictionaryDO dictionary){
		return dictionaryDao.save(dictionary);
	}
	
	@Override
	public int update(DictionaryDO dictionary){
		return dictionaryDao.update(dictionary);
	}
	
	@Override
	public int remove(Integer id){
		return dictionaryDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return dictionaryDao.batchRemove(ids);
	}

	//endregion


	/**
	 * 查询多个集合
	 * @param keys
	 * @return
	 */
	@Override
	public Map<Integer,String> queryByKeys(Integer... keys) {

		List<DictionaryDO> list = dictionaryDao.queryByKeys(keys);

		if (list == null || list.size() == 0) {
			return new HashMap<>();
		}

		Map<Integer, String> result = new HashMap<>();
		list.forEach((item) -> {
			result.put(item.getKey(), item.getVal());
		});

		return result;
	}

}
