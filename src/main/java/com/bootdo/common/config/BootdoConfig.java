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
	 * 测试环境
	 */
	private Boolean aliPayTest;

	public Boolean getAliPayTest() {
		return aliPayTest;
	}

	public void setAliPayTest(Boolean aliPayTest) {
		this.aliPayTest = aliPayTest;
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
