package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-31 09:44:20
 */
public class QrcodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer customerId;
	//
	private Integer deskId;
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
	 * 设置：
	 */
	public void setDeskId(Integer deskId) {
		this.deskId = deskId;
	}
	/**
	 * 获取：
	 */
	public Integer getDeskId() {
		return deskId;
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
