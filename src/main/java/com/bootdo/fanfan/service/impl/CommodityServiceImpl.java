package com.bootdo.fanfan.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.CommodityDO;
import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.CommoditStateEnum;
import com.bootdo.fanfan.service.CommodityExtendService;
import com.bootdo.fanfan.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommodityDao;
import org.springframework.util.CollectionUtils;


@Service
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	private CommodityDao commodityDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private CommodityExtendService commodityExtendService;
	@Autowired
	EMapper eMapper;

	@Override
	public CommodityDO get(Integer id){
		return commodityDao.get(id);
	}
	
	@Override
	public List<CommodityDO> list(Map<String, Object> map){

		return commodityDao.list(map);
	}

	@Override
	public CommodityWidthExtendDO getExtend(Integer id) {

		CommodityWidthExtendDO result = null;

		CommodityDO commodityDO = commodityDao.get(id);
		if(commodityDO!=null){
			result = eMapper.map(commodityDO,CommodityWidthExtendDO.class);
			List<CommodityExtendDO> extendDOS = commodityExtendService.listByCommodityId(id);
			result.setExtendList(extendDOS);
		}
		return result;
	}

	@Override
	public List<CommodityWidthExtendDO> listExtend(Map<String,Object> map){
		return commodityDao.queryExtends(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commodityDao.count(map);
	}
	
	@Override
	public int save(CommodityDO commodit){
		//设置当前时间
		commodit.setCreateTime(Calendar.getInstance().getTime());
		commodit.setStatus(CommoditStateEnum.useful.getVal());
		commodit.setDelete(BooleanEnum.False.getVal());
		if(commodit.getMustOrder()==null){
			commodit.setMustOrder(BooleanEnum.False.getVal());
		}
		return commodityDao.save(commodit);
	}

	/**
	 * 根据Id集合查询商品
	 * @param idArry
	 * @return
	 */
	@Override
	public List<CommodityWidthExtendDO> queryByIdArray(List<Integer> idArray){

		if(CollectionUtils.isEmpty(idArray)) {
			return null;
		}
		String strIdArray= StringUtils.join(idArray,",");

		Map<String,Object> params = new HashMap<>();
		params.put("idArray",strIdArray);

		return commodityDao.queryExtends(params);
	}

	@Override
	public int update(CommodityDO commodit){
		return commodityDao.update(commodit);
	}
	
	@Override
	public int remove(Integer id){
		return commodityDao.remove(id);
	}

	@Override
	public int updateStatus(Integer id, boolean pullOff) {
		return commodityDao.updateState(id, pullOff ? 2 : 1);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return commodityDao.batchRemove(ids);
	}
	
}
