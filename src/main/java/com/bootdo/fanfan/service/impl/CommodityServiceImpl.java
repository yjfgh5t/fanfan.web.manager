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
	public int save(CommodityDO commodityDO){
		//设置当前时间
		commodityDO.setCreateTime(Calendar.getInstance().getTime());
		commodityDO.setStatus(CommoditStateEnum.useful.getVal());
		commodityDO.setDelete(BooleanEnum.False.getVal());
		if(commodityDO.getMustOrder()==null){
			commodityDO.setMustOrder(BooleanEnum.False.getVal());
		}
		if(commodityDO.getRecommend()==null){
			commodityDO.setRecommend(0);
		}
		return commodityDao.save(commodityDO);
	}

	/**
	 * 修改为是否推荐商品
	 * @param recommend
	 * @param commodityId
	 * @return
	 */
	@Override
	public int setRecommend(Integer commodityId,Integer recommend,Integer customerId){

		//查询推荐商品数量
		Map<String, Object> map = new HashMap<>();
		map.put("customerId",customerId);
		map.put("delete","0");
		map.put("recommend","1");

		int count = commodityDao.count(map);
		if(count>4){
			return -1;
		}
		return commodityDao.updateRecommend(commodityId,recommend);
	}

	/**
	 * 查询推荐商品
	 * @param customerId
	 * @return
	 */
	@Override
	public List<CommodityDO> getRecommend(Integer customerId){
		return commodityDao.queryRecommend(customerId);
	}

	/**
	 * 根据Id集合查询商品
	 * @param idArray
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
