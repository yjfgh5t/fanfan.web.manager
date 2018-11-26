package com.bootdo.fanfan.service.impl;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.fanfan.domain.DTO.FormUserDTO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.FormIdDao;
import com.bootdo.fanfan.domain.FormIdDO;
import com.bootdo.fanfan.service.FormIdService;



@Service
public class FormIdServiceImpl implements FormIdService {
	@Autowired
	private FormIdDao formIdDao;
	
	@Override
	public FormIdDO get(Integer id){
		return formIdDao.get(id);
	}

	/**
	 * 获取一个有效的formId
	 * @return
	 */
	@Override
	public FormUserDTO getCanUseFormId(PlatformEnum platformEnum, Integer userId) {
		int count = platformEnum == PlatformEnum.AlipayMiniprogram ? 3 : 1;
		FormUserDTO userDTO = formIdDao.getCanUseFormId(platformEnum.getVal(), count, userId);
		if(userDTO!=null){
			formIdDao.addFormIdUseOnce(userDTO.getId());
		}
		return userDTO;
	}

	
	@Override
	public List<FormIdDO> list(Map<String, Object> map){
		return formIdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return formIdDao.count(map);
	}
	
	@Override
	public int save(FormIdDO model){

		model.setUseCount(0);

		//设置有效期为7天
		Calendar expiredTime = Calendar.getInstance();
		expiredTime.add(Calendar.DATE,7);
		model.setExpiredTime(expiredTime.getTime());

		return formIdDao.save(model);
	}
	
	@Override
	public int update(FormIdDO formId){
		return formIdDao.update(formId);
	}
	
	@Override
	public int remove(Integer id){
		return formIdDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return formIdDao.batchRemove(ids);
	}
	
}
