package com.bootdo.fanfan.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommoditCategoryDao;
import com.bootdo.fanfan.domain.CommoditCategoryDO;
import com.bootdo.fanfan.service.CommoditCategoryService;



@Service
public class CommoditCategoryServiceImpl implements CommoditCategoryService {
	@Autowired
	private CommoditCategoryDao commoditCategoryDao;
	
	@Override
	public CommoditCategoryDO get(Integer id){
		return commoditCategoryDao.get(id);
	}
	
	@Override
	public List<CommoditCategoryDO> list(Map<String, Object> map){
		return commoditCategoryDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commoditCategoryDao.count(map);
	}
	
	@Override
	public int save(CommoditCategoryDO commoditCategory){
		//设置当前时间
		commoditCategory.setCreateTime(Calendar.getInstance().getTime());
		return commoditCategoryDao.save(commoditCategory);
	}
	
	@Override
	public int update(CommoditCategoryDO commoditCategory){
		return commoditCategoryDao.update(commoditCategory);
	}
	
	@Override
	public int remove(Integer id){
		return commoditCategoryDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return commoditCategoryDao.batchRemove(ids);
	}
	
}
