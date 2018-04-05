package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:17
 */
public class ReceiverDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//用户主键
	private Integer userId;
	//收货人电话
	private String tel;
	//收货人名称
	private String name;
	//收货人性别
	private String sex;
	//区域
	private String district;
	//街道
	private String street;
	//详细地址
	private String detail;
	//精度
	private String lat;
	//纬度
	private String lng;
	//是否删除
	private Integer delete;

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
	 * 设置：用户主键
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户主键
	 */
	public Integer getUserId() {
		return userId;
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
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * 获取：区域
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * 设置：街道
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：街道
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：详细地址
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：详细地址
	 */
	public String getDetail() {
		return detail;
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
