package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-06-20 11:12:04
 */
public class CommodityCategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//分类名称
	private String name;
	//排序号
	private Integer order;
	//创建时间
	private Date createTime;
	//创建人
	private Integer customerId;
	//是否删除
	private Integer delete;

	/**
	 * 设置：sys_user用户主键-商户id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：sys_user用户主键-商户id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序号
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序号
	 */
	public Integer getOrder() {
		return order;
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
	/**
	 * 设置：创建人
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCustomerId() {
		return customerId;
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
}
