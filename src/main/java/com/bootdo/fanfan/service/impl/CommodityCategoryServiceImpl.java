package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommodityCategoryDao;
import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.fanfan.service.CommodityCategoryService;



@Service
public class CommodityCategoryServiceImpl implements CommodityCategoryService {
	@Autowired
	private CommodityCategoryDao commodityCategoryDao;
	
	@Override
	public CommodityCategoryDO get(Integer id){
		return commodityCategoryDao.get(id);
	}
	
	@Override
	public List<CommodityCategoryDO> list(Map<String, Object> map){
		List<CommodityCategoryDO> list = commodityCategoryDao.list(map);

		if(list==null)
			list = new ArrayList<>();

		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commodityCategoryDao.count(map);
	}
	
	@Override
	public int save(CommodityCategoryDO commoditCategory){
		//设置当前时间
		commoditCategory.setCreateTime(Calendar.getInstance().getTime());
		return commodityCategoryDao.save(commoditCategory);
	}
	
	@Override
	public int update(CommodityCategoryDO commoditCategory){
		return commodityCategoryDao.update(commoditCategory);
	}
	
	@Override
	public int remove(Integer id){
		return commodityCategoryDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return commodityCategoryDao.batchRemove(ids);
	}
	
}
