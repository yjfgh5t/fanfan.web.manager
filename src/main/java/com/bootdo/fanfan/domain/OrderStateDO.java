package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
public class OrderStateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单主键
	private Integer orderId;
	//订单状态
	private Integer orderState;
	//创建时间
	private Date createTime;
	//用户主键
	private Integer createId;

	public OrderStateDO(){

	}

	public OrderStateDO(Integer orderId,Integer orderState,Integer createId){
		this.orderId=orderId;
		this.orderState=orderState;
		this.createId=createId;
	}

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
	 * 设置：订单主键
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单主键
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：订单状态
	 */
	public Integer getOrderState() {
		return orderState;
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
	 * 设置：用户主键
	 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**
	 * 获取：用户主键
	 */
	public Integer getCreateId() {
		return createId;
	}
}
