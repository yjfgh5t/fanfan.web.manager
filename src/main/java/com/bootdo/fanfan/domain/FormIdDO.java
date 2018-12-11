package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-10 17:02:40
 */
public class FormIdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//顾客Id
	private Integer userId;
	//表单id
	private String formId;
	//第三方Id
	private String tpId;
	//过期时间
	private Date expiredTime;
	//使用次数 支付包可使用3次 微信1次
	private Integer useCount;
	//form_id类型 1：支付宝 2：微信
	private Integer formType;

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
	 * 设置：顾客Id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：顾客Id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：表单id
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}
	/**
	 * 获取：表单id
	 */
	public String getFormId() {
		return formId;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}
	/**
	 * 设置：使用次数 支付包可使用3次 微信1次
	 */
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	/**
	 * 获取：使用次数 支付包可使用3次 微信1次
	 */
	public Integer getUseCount() {
		return useCount;
	}
	/**
	 * 设置：form_id类型 1：支付宝 2：微信
	 */
	public void setFormType(Integer formType) {
		this.formType = formType;
	}
	/**
	 * 获取：form_id类型 1：支付宝 2：微信
	 */
	public Integer getFormType() {
		return formType;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
}
