package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:07:45
 */
public class TokenDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//商户Id
	private Integer customerId;
	//APPID
	private String appId;
	//令牌
	private String appToken;
	//刷新的令牌
	private String appRefreshToken;
	//Token到期时间
	private Date appExpries;
	//授权用户Id
	private String appUserId;
	//Token类型
	private Integer platformType;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：商户Id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：商户Id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：APPID
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * 获取：APPID
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * 设置：令牌
	 */
	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}
	/**
	 * 获取：令牌
	 */
	public String getAppToken() {
		return appToken;
	}
	/**
	 * 设置：刷新的令牌
	 */
	public void setAppRefreshToken(String appRefreshToken) {
		this.appRefreshToken = appRefreshToken;
	}
	/**
	 * 获取：刷新的令牌
	 */
	public String getAppRefreshToken() {
		return appRefreshToken;
	}
	/**
	 * 设置：Token到期时间
	 */
	public void setAppExpries(Date appExpries) {
		this.appExpries = appExpries;
	}
	/**
	 * 获取：Token到期时间
	 */
	public Date getAppExpries() {
		return appExpries;
	}
	/**
	 * 设置：授权用户Id
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}
	/**
	 * 获取：授权用户Id
	 */
	public String getAppUserId() {
		return appUserId;
	}
	/**
	 * 设置：Token类型
	 */
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	/**
	 * 获取：Token类型
	 */
	public Integer getPlatformType() {
		return platformType;
	}
}
