package com.bootdo.fanfan.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.CommoditStateEnum;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.CommoditDao;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;
import org.springframework.util.CollectionUtils;


@Service
public class CommoditServiceImpl implements CommoditService {
	@Autowired
	private CommoditDao commoditDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@Override
	public CommoditDO get(Integer id){
		return commoditDao.get(id);
	}
	
	@Override
	public List<CommoditDO> list(Map<String, Object> map){

		return commoditDao.list(map);
	}

	@Override
	public List<CommodityWidthExtendDO> listExtend(Map<String,Object> map){
		return commoditDao.queryExtends(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return commoditDao.count(map);
	}
	
	@Override
	public int save(CommoditDO commodit){
		//设置当前时间
		commodit.setCreateTime(Calendar.getInstance().getTime());
		commodit.setStatus(CommoditStateEnum.useful.getVal());
		commodit.setDelete(BooleanEnum.False.getVal());
		if(commodit.getMustOrder()==null){
			commodit.setMustOrder(BooleanEnum.False.getVal());
		}
		return commoditDao.save(commodit);
	}

	/**
	 * 根据Id集合查询商品
	 * @param idArry
	 * @return
	 */
	@Override
	public List<CommoditDO> queryByIdarry(List<Integer> idArry){

		if(CollectionUtils.isEmpty(idArry)) {
			return null;
		}

		String strIdarry= StringUtils.join(idArry,",");

		return commoditDao.queryByIdArry(strIdarry);
	}

	@Override
	public int update(CommoditDO commodit){
		return commoditDao.update(commodit);
	}
	
	@Override
	public int remove(Integer id){
		return commoditDao.remove(id);
	}

	@Override
	public int updateStatus(Integer id, boolean pullOff) {
		return commoditDao.updateState(id, pullOff ? 2 : 1);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return commoditDao.batchRemove(ids);
	}
	
}
