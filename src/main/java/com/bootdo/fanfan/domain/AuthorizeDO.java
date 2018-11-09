package com.bootdo.fanfan.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-07 12:12:49
 */
public class AuthorizeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//认证状态
	private Integer authorizeState;
	//审核状态
	private Integer identificationState;
	//收款账号
	private String payeeId;
	//收款人姓名
	private String payeeName;
	//营业执照
	private String businessLicensePhoto;
	//营业执照截止时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date businessLicenseDate;
	//身份证照
	private String idCardPhoto;
	//店铺门面照
	private String shopPhoto;
	//审核失败原因
	private String failRemark;
	//认证成功地址
	private String identificationUrl;
	//商户Id
	private Integer customerId;
	//创建时间
	private Date createTime;
	//修改时间
	private Date modifyTime;

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
	 * 设置：认证状态
	 */
	public void setAuthorizeState(Integer authorizeState) {
		this.authorizeState = authorizeState;
	}
	/**
	 * 获取：认证状态
	 */
	public Integer getAuthorizeState() {
		return authorizeState;
	}
	/**
	 * 设置：审核状态
	 */
	public void setIdentificationState(Integer identificationState) {
		this.identificationState = identificationState;
	}
	/**
	 * 获取：审核状态
	 */
	public Integer getIdentificationState() {
		return identificationState;
	}
	/**
	 * 设置：收款账号
	 */
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	/**
	 * 获取：收款账号
	 */
	public String getPayeeId() {
		return payeeId;
	}
	/**
	 * 设置：收款人姓名
	 */
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	/**
	 * 获取：收款人姓名
	 */
	public String getPayeeName() {
		return payeeName;
	}
	/**
	 * 设置：营业执照
	 */
	public void setBusinessLicensePhoto(String businessLicensePhoto) {
		this.businessLicensePhoto = businessLicensePhoto;
	}
	/**
	 * 获取：营业执照
	 */
	public String getBusinessLicensePhoto() {
		return businessLicensePhoto;
	}
	/**
	 * 设置：营业执照截止时间
	 */
	public void setBusinessLicenseDate(Date businessLicenseDate) {
		this.businessLicenseDate = businessLicenseDate;
	}
	/**
	 * 获取：营业执照截止时间
	 */
	public Date getBusinessLicenseDate() {
		return businessLicenseDate;
	}
	/**
	 * 设置：身份证照
	 */
	public void setIdCardPhoto(String idCardPhoto) {
		this.idCardPhoto = idCardPhoto;
	}
	/**
	 * 获取：身份证照
	 */
	public String getIdCardPhoto() {
		return idCardPhoto;
	}
	/**
	 * 设置：店铺门面照
	 */
	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}
	/**
	 * 获取：店铺门面照
	 */
	public String getShopPhoto() {
		return shopPhoto;
	}
	/**
	 * 设置：审核失败原因
	 */
	public void setFailRemark(String failRemark) {
		this.failRemark = failRemark;
	}
	/**
	 * 获取：审核失败原因
	 */
	public String getFailRemark() {
		return failRemark;
	}
	/**
	 * 设置：认证成功地址
	 */
	public void setIdentificationUrl(String identificationUrl) {
		this.identificationUrl = identificationUrl;
	}
	/**
	 * 获取：认证成功地址
	 */
	public String getIdentificationUrl() {
		return identificationUrl;
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
	 * 设置：修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
}
