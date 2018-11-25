package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-25 21:10:26
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
	//是否开启支付宝
	private Integer alipay;
	//是否开启微信
	private Integer wechat;
	//是否线下支付
	private Integer offline;
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
	 * 设置：是否开启支付宝
	 */
	public void setAlipay(Integer alipay) {
		this.alipay = alipay;
	}
	/**
	 * 获取：是否开启支付宝
	 */
	public Integer getAlipay() {
		return alipay;
	}
	/**
	 * 设置：是否开启微信
	 */
	public void setWechat(Integer wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取：是否开启微信
	 */
	public Integer getWechat() {
		return wechat;
	}
	/**
	 * 设置：是否线下支付
	 */
	public void setOffline(Integer offline) {
		this.offline = offline;
	}
	/**
	 * 获取：是否线下支付
	 */
	public Integer getOffline() {
		return offline;
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
