package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-12-13 09:26:51
 */
public class ShopDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//店铺名称
	private String name;
	//营业起始时间
	private String businessStart;
	//营业结束时间
	private String businessEnd;
	//店铺头像
	private String logo;
	//店铺状态 1：正常营业  2：停止营业
	private Integer state;
	//最低订单价格
	private Float minOrderPrice;
	//店铺地址
	private String address;
	//开始支付宝收款
	private Integer alipay;
	//开启微信收款
	private Integer wechat;
	//开启线下收款
	private Integer offline;
	//精度
	private String lat;
	//维度
	private String lng;
	//商家联系电话
	private String telephone;
	//是否开启外卖
	private Integer takeout;
	//配送范围
	private Double deliveryRange;
	//配送费
	private BigDecimal deliveryCost;
	//创建时间
	private Date createTime;
	//结束时间
	private Date modifyTime;
	//商户Id
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
	 * 设置：店铺名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：店铺名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：营业起始时间
	 */
	public void setBusinessStart(String businessStart) {
		this.businessStart = businessStart;
	}
	/**
	 * 获取：营业起始时间
	 */
	public String getBusinessStart() {
		return businessStart;
	}
	/**
	 * 设置：营业结束时间
	 */
	public void setBusinessEnd(String businessEnd) {
		this.businessEnd = businessEnd;
	}
	/**
	 * 获取：营业结束时间
	 */
	public String getBusinessEnd() {
		return businessEnd;
	}
	/**
	 * 设置：店铺头像
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 获取：店铺头像
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * 设置：店铺状态 1：正常营业  2：停止营业
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：店铺状态 1：正常营业  2：停止营业
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：最低订单价格
	 */
	public void setMinOrderPrice(Float minOrderPrice) {
		this.minOrderPrice = minOrderPrice;
	}
	/**
	 * 获取：最低订单价格
	 */
	public Float getMinOrderPrice() {
		return minOrderPrice;
	}
	/**
	 * 设置：店铺地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：店铺地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：开始支付宝收款
	 */
	public void setAlipay(Integer alipay) {
		this.alipay = alipay;
	}
	/**
	 * 获取：开始支付宝收款
	 */
	public Integer getAlipay() {
		return alipay;
	}
	/**
	 * 设置：开启微信收款
	 */
	public void setWechat(Integer wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取：开启微信收款
	 */
	public Integer getWechat() {
		return wechat;
	}
	/**
	 * 设置：开启线下收款
	 */
	public void setOffline(Integer offline) {
		this.offline = offline;
	}
	/**
	 * 获取：开启线下收款
	 */
	public Integer getOffline() {
		return offline;
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
	 * 设置：维度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：维度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：商家联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：商家联系电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：是否开启外卖
	 */
	public void setTakeout(Integer takeout) {
		this.takeout = takeout;
	}
	/**
	 * 获取：是否开启外卖
	 */
	public Integer getTakeout() {
		return takeout;
	}
	/**
	 * 设置：配送范围
	 */
	public void setDeliveryRange(Double deliveryRange) {
		this.deliveryRange = deliveryRange;
	}
	/**
	 * 获取：配送范围
	 */
	public Double getDeliveryRange() {
		return deliveryRange;
	}
	/**
	 * 设置：配送费
	 */
	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	/**
	 * 获取：配送费
	 */
	public BigDecimal getDeliveryCost() {
		return deliveryCost;
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
	 * 设置：结束时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	/**
	 * 设置：商户Id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：商户Id
	 */
	public Integer getCustomerId() {
		return customerId;
	}
}
