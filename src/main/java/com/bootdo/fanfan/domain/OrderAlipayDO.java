package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:34:51
 */
public class OrderAlipayDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单表主键
	private Integer id;
	//订单内容
	private String body;
	//订单名称
	private String subject;
	//外部订单号
	private String tradeNo;
	//订单过期时间 10m - 10分钟
	private String timeoutExpress;
	//总金额
	private String totalAmount;
	//产品码 固定值
	private String productCode;
	//商品类型 0：虚拟商品 1:实物
	private String goodsType;
	//回调时返回参数
	private String passbackParams;
	//商户id 可不填
	private String storeId;
	//创建支付宝订单时返回内容
	private String createBackBody;
	//创建时间
	private Date createTime;
	//服务回调时间
	private Date backTime;

	/**
	 * 设置：订单表主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：订单表主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：订单内容
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取：订单内容
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置：订单名称
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 获取：订单名称
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * 设置：外部订单号
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 获取：外部订单号
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * 设置：订单过期时间 10m - 10分钟
	 */
	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}
	/**
	 * 获取：订单过期时间 10m - 10分钟
	 */
	public String getTimeoutExpress() {
		return timeoutExpress;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：总金额
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：产品码 固定值
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 获取：产品码 固定值
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 设置：商品类型 0：虚拟商品 1:实物
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	/**
	 * 获取：商品类型 0：虚拟商品 1:实物
	 */
	public String getGoodsType() {
		return goodsType;
	}
	/**
	 * 设置：回调时返回参数
	 */
	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
	/**
	 * 获取：回调时返回参数
	 */
	public String getPassbackParams() {
		return passbackParams;
	}
	/**
	 * 设置：商户id 可不填
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：商户id 可不填
	 */
	public String getStoreId() {
		return storeId;
	}
	/**
	 * 设置：创建支付宝订单时返回内容
	 */
	public void setCreateBackBody(String createBackBody) {
		this.createBackBody = createBackBody;
	}
	/**
	 * 获取：创建支付宝订单时返回内容
	 */
	public String getCreateBackBody() {
		return createBackBody;
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
	 * 设置：服务回调时间
	 */
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	/**
	 * 获取：服务回调时间
	 */
	public Date getBackTime() {
		return backTime;
	}
}
