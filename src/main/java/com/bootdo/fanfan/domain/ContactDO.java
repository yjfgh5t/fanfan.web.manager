package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-22 11:16:13
 */
public class ContactDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//联系人
	private String contact;
	//联系电话
	private String telephone;
	//留言
	private String describe;
	//备注
	private String remark;
	//创建时间
	private Date createTime;

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
	 * 设置：联系人
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * 获取：联系人
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * 设置：联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：留言
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	/**
	 * 获取：留言
	 */
	public String getDescribe() {
		return describe;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
