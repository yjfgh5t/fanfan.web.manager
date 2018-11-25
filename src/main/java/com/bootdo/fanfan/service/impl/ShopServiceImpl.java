package com.bootdo.fanfan.service.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.ShopDao;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.service.ShopService;



@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;
	
	@Override
	public ShopDO get(Integer id){
		return shopDao.get(id);
	}

	@Override
	public ShopDO getByCustomerId(Integer customerId){
		return shopDao.getByCustomerId(customerId);
	}

	@Override
	public ShopDO getPayType(Integer id){
		return shopDao.getPayType(id);
	}

	@Override
	public String getNameByCustomerId(Integer customerId){
		return shopDao.getNameByCustomerId(customerId);
	}
	
	@Override
	public List<ShopDO> list(Map<String, Object> map){
		return shopDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return shopDao.count(map);
	}
	
	@Override
	public int save(ShopDO shop){
		shop.setModifyTime(new Date());
		shop.setCreateTime(new Date());
		int count = shopDao.save(shop);
		//保存成功 返回Id
		return count;
	}
	
	@Override
	public int update(ShopDO shop){
		shop.setModifyTime(new Date());
		return shopDao.update(shop);
	}
	
	@Override
	public int remove(Integer id){
		return shopDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return shopDao.batchRemove(ids);
	}
	
}
