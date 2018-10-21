package com.bootdo.fanfan.service.impl;

import com.bootdo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommodityExtendDao;
import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.service.CommodityExtendService;
import org.springframework.util.CollectionUtils;


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
		commodityExtend.setCreateTime(new Date());
		commodityExtend.setDelete(0);
		return commodityExtendDao.save(commodityExtend);
	}
	
	@Override
	public int update(CommodityExtendDO commodityExtend){
		return commodityExtendDao.update(commodityExtend);
	}

	/**
	 * 修改客户端删除的规格
	 * @param idArrays
	 * @param commodityId
	 * @return
	 */
	public boolean updateDeletes(List<Integer> idArrays,Integer commodityId){
		if(CollectionUtils.isEmpty(idArrays)) {
			return false;
		}
		String strIdarry= StringUtils.join(idArrays,",");
		return commodityExtendDao.updateDeletes(strIdarry,commodityId)>0;
	}

	@Override
	public List<CommodityExtendDO> listByCommodityId(Integer commodityId) {
		Map<String,Object> params = new HashMap<>();
		params.put("commodityId",commodityId);
		params.put("delete","0");
		params.put("sort","create_time");
		params.put("order","asc");
		return commodityExtendDao.list(params);
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
