package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-07-03 12:18:24
 */
public class DeskDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//桌号
	private String title;
	//商户id
	private Integer customerId;

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
	 * 设置：桌号
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：桌号
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：商户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：商户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
}
