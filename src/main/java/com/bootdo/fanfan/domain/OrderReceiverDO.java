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
public class OrderReceiverDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单主键
	private Integer id;
	//收货人电话
	private String tel;
	//收货人名称
	private String name;
	//收货人性别
	private String sex;
	//区域
	private String addr;
	//详细地址
	private String addrDetail;
	//精度
	private String lat;
	//纬度
	private String lng;
	//配送距离
	private Double deliveryRange;
	//配送人
	private String deliveryName;
	//配送人电话
	private String deliveryTel;
	//修改时间
	private Date updateTime;

	/**
	 * 设置：订单主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：订单主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：收货人电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：收货人电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：收货人名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：收货人名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：收货人性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：收货人性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：区域
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 获取：区域
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddrDetail() {
		return addrDetail;
	}
	/**
	 * 设置：精度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：精度
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * 设置：纬度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：纬度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：配送距离
	 */
	public void setDeliveryRange(Double deliveryRange) {
		this.deliveryRange = deliveryRange;
	}
	/**
	 * 获取：配送距离
	 */
	public Double getDeliveryRange() {
		return deliveryRange;
	}
	/**
	 * 设置：配送人
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	/**
	 * 获取：配送人
	 */
	public String getDeliveryName() {
		return deliveryName;
	}
	/**
	 * 设置：配送人电话
	 */
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	/**
	 * 获取：配送人电话
	 */
	public String getDeliveryTel() {
		return deliveryTel;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
