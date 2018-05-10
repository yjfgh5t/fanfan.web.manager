package com.bootdo.fanfan.domain;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-11 15:33:59
 */
@ToString
public class AlipayRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String tradeNo;
	//
	private Date notifyTime;
	//
	private String notifyType;
	//
	private String notifyId;
	//
	private String appId;
	//
	private String charset;
	//
	private String version;
	//
	private String signType;
	//
	private String outTradeNo;
	//
	private String outBizNo;
	//
	private String buyerId;
	//
	private String buyerLogonId;
	//
	private String sellerId;
	//
	private String sellerEmail;
	//
	private String tradeStatus;
	//
	private BigDecimal totalAmount;
	//
	private BigDecimal receiptAmount;
	//
	private BigDecimal invoiceAmount;
	//
	private BigDecimal buyerPayAmount;
	//
	private BigDecimal pointAmount;
	//
	private BigDecimal refundFee;
	//
	private String subject;
	//
	private String body;
	//
	private Date gmtCreate;
	//
	private Date gmtPayment;
	//
	private Date gmtRefund;
	//
	private Date gmtClose;
	//
	private String fundBillList;
	//
	private String passbackParams;
	//
	private String voucherDetailList;

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
	 * 设置：
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 获取：
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * 设置：
	 */
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	/**
	 * 获取：
	 */
	public Date getNotifyTime() {
		return notifyTime;
	}
	/**
	 * 设置：
	 */
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	/**
	 * 获取：
	 */
	public String getNotifyType() {
		return notifyType;
	}
	/**
	 * 设置：
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	/**
	 * 获取：
	 */
	public String getNotifyId() {
		return notifyId;
	}
	/**
	 * 设置：
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * 获取：
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * 设置：
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	/**
	 * 获取：
	 */
	public String getCharset() {
		return charset;
	}
	/**
	 * 设置：
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}
	/**
	 * 获取：
	 */
	public String getSignType() {
		return signType;
	}
	/**
	 * 设置：
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * 获取：
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * 设置：
	 */
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
	/**
	 * 获取：
	 */
	public String getOutBizNo() {
		return outBizNo;
	}
	/**
	 * 设置：
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * 获取：
	 */
	public String getBuyerId() {
		return buyerId;
	}
	/**
	 * 设置：
	 */
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	/**
	 * 获取：
	 */
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	/**
	 * 设置：
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * 获取：
	 */
	public String getSellerId() {
		return sellerId;
	}
	/**
	 * 设置：
	 */
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	/**
	 * 获取：
	 */
	public String getSellerEmail() {
		return sellerEmail;
	}
	/**
	 * 设置：
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	/**
	 * 获取：
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}
	/**
	 * 设置：
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：
	 */
	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}
	/**
	 * 设置：
	 */
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	/**
	 * 设置：
	 */
	public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getBuyerPayAmount() {
		return buyerPayAmount;
	}
	/**
	 * 设置：
	 */
	public void setPointAmount(BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPointAmount() {
		return pointAmount;
	}
	/**
	 * 设置：
	 */
	public void setRefundFee(BigDecimal refundFee) {
		this.refundFee = refundFee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getRefundFee() {
		return refundFee;
	}
	/**
	 * 设置：
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * 获取：
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * 设置：
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取：
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置：
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：
	 */
	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	/**
	 * 获取：
	 */
	public Date getGmtPayment() {
		return gmtPayment;
	}
	/**
	 * 设置：
	 */
	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}
	/**
	 * 获取：
	 */
	public Date getGmtRefund() {
		return gmtRefund;
	}
	/**
	 * 设置：
	 */
	public void setGmtClose(Date gmtClose) {
		this.gmtClose = gmtClose;
	}
	/**
	 * 获取：
	 */
	public Date getGmtClose() {
		return gmtClose;
	}
	/**
	 * 设置：
	 */
	public void setFundBillList(String fundBillList) {
		this.fundBillList = fundBillList;
	}
	/**
	 * 获取：
	 */
	public String getFundBillList() {
		return fundBillList;
	}
	/**
	 * 设置：
	 */
	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
	/**
	 * 获取：
	 */
	public String getPassbackParams() {
		return passbackParams;
	}
	/**
	 * 设置：
	 */
	public void setVoucherDetailList(String voucherDetailList) {
		this.voucherDetailList = voucherDetailList;
	}
	/**
	 * 获取：
	 */
	public String getVoucherDetailList() {
		return voucherDetailList;
	}
}
