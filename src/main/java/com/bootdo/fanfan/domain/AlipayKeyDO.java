package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-13 15:48:27
 */
public class AlipayKeyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//公钥
	private String publicKey;
	//私钥
	private String privateKey;
	//appId
	private String appId;
	//支付宝公钥
	private String publicTbKey;
	//商户Id
	private Integer customerId;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：公钥
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	/**
	 * 获取：公钥
	 */
	public String getPublicKey() {
		return publicKey;
	}
	/**
	 * 设置：私钥
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * 获取：私钥
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	/**
	 * 设置：appId
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * 获取：appId
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * 设置：支付宝公钥
	 */
	public void setPublicTbKey(String publicTbKey) {
		this.publicTbKey = publicTbKey;
	}
	/**
	 * 获取：支付宝公钥
	 */
	public String getPublicTbKey() {
		return publicTbKey;
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
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
