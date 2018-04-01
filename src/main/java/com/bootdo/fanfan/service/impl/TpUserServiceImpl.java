package com.bootdo.fanfan.service.impl;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.TpUserDao;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.service.TpUserService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TpUserServiceImpl implements TpUserService {
	@Autowired
	private TpUserDao tpUserDao;

	@Autowired
	private AlipayManager  alipayManager;

	@Autowired
	private EMapper eMapper;

	@Autowired
	private UserService userService;

	@Override
	public TpUserDO get(Integer id){
		return tpUserDao.get(id);
	}

	@Override
	@Transactional(rollbackFor = {NumberFormatException.class})
	public TpUserDO getTPInfo(String code, int type) {

		TpUserDO tpUserDO = getTpUserDO(code, type);

		if(tpUserDO==null)
			return null;

		//查询数据中是否存在
		TpUserDO  dbTpUserDO  = tpUserDao.getByTpId(tpUserDO.getTpId());

		if(dbTpUserDO!=null)
			return dbTpUserDO;

		//转化未 用户信息
		UserDO userDO = eMapper.map(tpUserDO,UserDO.class);

		//添加用户至信息表
		int  userId = userService.save(userDO);
		if(userId==0){
			//抛出异常
			Integer.parseInt("AS");
		}

		//添加至TpUser表
		tpUserDO.setUserId(userId);

		//保存 并设置 Id
		tpUserDO.setId(this.save(tpUserDO));

		return tpUserDO;
	}

	@Override
	public List<TpUserDO> list(Map<String, Object> map){
		return tpUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tpUserDao.count(map);
	}
	
	@Override
	public int save(TpUserDO tpUser){
		tpUser.setCreateTime(Calendar.getInstance().getTime());
		return tpUserDao.save(tpUser);
	}
	
	@Override
	public int update(TpUserDO tpUser){
		return tpUserDao.update(tpUser);
	}
	
	@Override
	public int remove(Integer id){
		return tpUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tpUserDao.batchRemove(ids);
	}

	//region 私有方法

	/**
	 * 获取第三方用户信息
	 * @param code
	 * @param type
	 * @return
	 */
	private TpUserDO getTpUserDO(String code, int type) {
		TpUserDO tpUserDO=null;

		if(type==1)
		{
			//调用接口返回
			AlipayUserInfoShareResponse alipayUserInfoShareResponse = alipayManager.getUserInfo(code);
			//数据转换
			if(alipayUserInfoShareResponse!=null) {
				tpUserDO = eMapper.map(alipayUserInfoShareResponse, TpUserDO.class);
				tpUserDO.setTpType(type);
				tpUserDO.setTpAppId(AlipayConfig.appId);
				//设置性别
				tpUserDO.setTpSex(alipayUserInfoShareResponse.getGender().toLowerCase().equals("M")?2:3);
			}
		}

		if(tpUserDO!=null) {
			tpUserDO.setCreateTime(Calendar.getInstance().getTime());
		}
		return tpUserDO;
	}

	//endregion
}
