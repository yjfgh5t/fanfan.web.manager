package com.bootdo.fanfan.service.impl;

import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.common.exception.BDException;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.manager.WechatManager;
import com.bootdo.fanfan.manager.model.wechat.WXJSCodeModel;
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

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	private WechatManager wechatManager;

	@Override
	public TpUserDO get(Integer id){
		return tpUserDao.get(id);
	}

	@Override
	public TpUserDO getByUserId(Integer userId) {
		return tpUserDao.getByUserId(userId);
	}

	/**
	 * 设置支付宝用户信息
	 * @param code
	 * @param platformEnum
	 * @param customerId
	 * @return
	 */
	@Override
	public TpUserDO setAlipayTPInfo(String code, PlatformEnum platformEnum,Integer customerId){
		TpUserDO tpUserDO = getTpUserDO(code, platformEnum,customerId);
		return getTPInfo(tpUserDO,platformEnum,customerId);
	}

	@Override
	public TpUserDO checkTPCode(String code, PlatformEnum platformEnum) {

		String tpId=null;

		//查询支付宝Code
		if(platformEnum==PlatformEnum.AlipayMiniprogram){
			AlipaySystemOauthTokenResponse token = alipayManager.getToken(code);
			if(token!=null){
				tpId = token.getUserId();
			}
		}else if(platformEnum ==PlatformEnum.WechatMiniprogram){
			//查询微信Code
			WXJSCodeModel codeModel = wechatManager.getJsCodeModel(code);
			if(codeModel!=null){
				tpId = codeModel.getOpenid();
			}
		}

		//查询用户信息
		if(StringUtils.isNotEmpty(tpId)){
			return tpUserDao.getByTpId(tpId);
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = {Exception.class,BDException.class})
	public TpUserDO getTPInfo(TpUserDO tpUserDO, PlatformEnum platformEnum,Integer customerId) {

		if(tpUserDO!=null) {

			//查询数据中是否存在
			TpUserDO dbTpUserDO = tpUserDao.getByTpId(tpUserDO.getTpId());

			if (dbTpUserDO != null) {
				return dbTpUserDO;
			}

			//转化未 用户信息
			UserDO userDO = eMapper.map(tpUserDO, UserDO.class);

			//添加用户至信息表
			if (userService.save(userDO) < 0) {
				//抛出异常
				throw new BDException("保存用户失败", BDException.BUSINESS_ERROR_CODE);
			}

			//添加至TpUser表
			tpUserDO.setUserId(userDO.getId());

			//保存
			this.save(tpUserDO);
		}

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
	public int save(TpUserDO tpUser) {
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

	/**
	 * 获取第三方用户Id
	 * @param userid
	 * @param appId
	 * @return
	 */
	@Override
	public String getTpId(Integer userid,String appId){
		return tpUserDao.getTpId(userid,appId);
	}

	//region 私有方法

	/**
	 * 获取第三方用户信息
	 * @param code
	 * @param platformEnum
	 * @return
	 */
	private TpUserDO getTpUserDO(String code, PlatformEnum platformEnum, Integer customerId) {
		TpUserDO tpUserDO=null;

		if(platformEnum==PlatformEnum.AlipayMiniprogram)
		{
			//调用接口返回
			AlipayUserInfoShareResponse alipayUserInfoShareResponse = alipayManager.getUserInfo(code,customerId);
			//数据转换
			if(alipayUserInfoShareResponse!=null) {
				tpUserDO = eMapper.map(alipayUserInfoShareResponse, TpUserDO.class);
				tpUserDO.setTpType(platformEnum.getVal());
				tpUserDO.setTpAppId(alipayConfig.getAppId());
				//设置性别
				if(StringUtils.isNotEmpty(alipayUserInfoShareResponse.getGender())) {
					tpUserDO.setTpSex(alipayUserInfoShareResponse.getGender().toLowerCase().equals("m") ? 2 : 3);
				}else{
					tpUserDO.setTpSex(1);
				}
			}
		}

		if(tpUserDO!=null) {
			tpUserDO.setCreateTime(Calendar.getInstance().getTime());
		}
		return tpUserDO;
	}

	//endregion
}
