package com.bootdo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="bootdo")
public class BootdoConfig {
	//上传路径
	private String uploadPath;

	/**
	 * 静态资源所在地址
	 */
	private String staticUrl;

	/**
	 * 信鸽应用Id
	 */
	private String xgAppId;

	/**
	 * 信鸽账号
	 */
	private Integer xgAccessId;

	/**
	 * 信鸽Key
	 */
	private String xgSecretKey;

	/**
	 * 默认支付宝小程序Id
	 */
	private String aliPayAppId;

	/**
	 * 默认支付宝平台
	 */
	private String aliPayPlatformAppId;

	/**
	 * 支付宝地址
	 */
	private String aliPayUrl;

	/**
	 * 授权地址
	 */
	private String authorizeUrl;

	/**
	 * 默认展示的商户
	 */
	private Integer defaultCustomerId;

	/**
	 * 支付成功通知地址
	 */
	private String payNotifyUrl;

	/**
	 * 微信应用Id
	 */
	private String wxAppId;

	/**
	 * 微信接口地址
	 */
	private String wxUrlPath;

	public String getUploadImgPath(){
		return  uploadPath+"img/";
	}
}
