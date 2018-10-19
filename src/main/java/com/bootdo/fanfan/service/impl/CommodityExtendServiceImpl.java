package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommodityExtendDao;
import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.service.CommodityExtendService;



@Service
public class CommodityExtendServiceImpl implements CommodityExtendService {
	@Autowired
	private CommodityExtendDao commodityExtendDao;
	
	@Override
	public CommodityExtendDO get(Integer id){
		return commodityExtendDao.get(id);
	}
	
	@Override
	public List<CommodityExtendDO> list(Map<String, Object> map){
		return commodityExtendDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commodityExtendDao.count(map);
	}
	
	@Override
	public int save(CommodityExtendDO commodityExtend){
		return commodityExtendDao.save(commodityExtend);
	}
	
	@Override
	public int update(CommodityExtendDO commodityExtend){
		return commodityExtendDao.update(commodityExtend);
	}
	
	@Override
	public int remove(Integer id){
		return commodityExtendDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return commodityExtendDao.batchRemove(ids);
	}
	
}
