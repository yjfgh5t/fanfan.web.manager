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

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
}
