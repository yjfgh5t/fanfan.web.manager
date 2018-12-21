package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-12-12 16:04:29
 */
public class DeliveryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//配送人姓名
	private String name;
	//配送人电话
	private String tel;
	//默认配送员
	private Integer isDefault;
	//是否删除
	private Integer delete;
	//sys_user用户主键-商户id
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
	 * 设置：配送人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：配送人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：配送人电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：配送人电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：默认配送员
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：默认配送员
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDelete() {
		return delete;
	}
	/**
	 * 设置：sys_user用户主键-商户id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：sys_user用户主键-商户id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
}
