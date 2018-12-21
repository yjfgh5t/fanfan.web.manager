package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-12-12 16:02:18
 */
public class QrcodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer customerId;
	//码标记
	private String desc;
	//二维码模板id
	private String templateId;
	//
	private Date createTime;
	//
	private Date modifyTime;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：码标记
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：码标记
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：二维码模板id
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	/**
	 * 获取：二维码模板id
	 */
	public String getTemplateId() {
		return templateId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
}
