package com.bootdo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
	 * 信鸽账号
	 */
	private Integer xgAccessId;

	/**
	 * 信鸽Key
	 */
	private String xgSecretKey;

	/**
	 * 默认支付宝小程序
	 */
	private Integer defaultAlipayCustomerId;

	/**
	 * 默认支付宝平台
	 */
	private Integer defaultAilpayPlatformCustomerId;

	/**
	 * 支付宝地址
	 */
	private String alipayUrl;

	public String getAlipayUrl() {
		return alipayUrl;
	}

	public void setAlipayUrl(String alipayUrl) {
		this.alipayUrl = alipayUrl;
	}

	public Integer getDefaultAlipayCustomerId() {
		return defaultAlipayCustomerId;
	}

	public void setDefaultAlipayCustomerId(Integer defaultAlipayCustomerId) {
		this.defaultAlipayCustomerId = defaultAlipayCustomerId;
	}

	public Integer getDefaultAilpayPlatformCustomerId() {
		return defaultAilpayPlatformCustomerId;
	}

	public void setDefaultAilpayPlatformCustomerId(Integer defaultAilpayPlatformCustomerId) {
		this.defaultAilpayPlatformCustomerId = defaultAilpayPlatformCustomerId;
	}

	public Integer getXgAccessId() {
		return xgAccessId;
	}

	public void setXgAccessId(Integer xgAccessId) {
		this.xgAccessId = xgAccessId;
	}

	public String getXgSecretKey() {
		return xgSecretKey;
	}

	public void setXgSecretKey(String xgSecretKey) {
		this.xgSecretKey = xgSecretKey;
	}

	public String getStaticUrl() {
		return staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public String getUploadImgPath(){
		return  uploadPath+"img/";
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
}
