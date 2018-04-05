package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-05 14:05:16
 */
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//订单号
	private String orderNum;
	//订单状态：101：创建订单  102：待支付 103：已支付 104:超时未支付 105：确认收单 106：异常订单反馈 201：确认收单 202:取消收单-退款 203:开始派单
	private Integer orderState;
	//订单总额
	private BigDecimal orderTotal;
	//支付金额
	private BigDecimal orderPay;
	//订单优惠总额
	private BigDecimal orderDiscountTotal;
	//订单商品总数量
	private Integer orderCommodityTotal;
	//订单支付类型  1:支付宝  2：微信 3：现金 
	private Integer orderPayType;
	//用餐人数
	private String orderMealsNum;
	//描述
	private String orderRemark;
	//订单发票信息
	private String orderInvoice;
	//用户主键
	private Integer createId;
	//创建时间
	private Date createTime;

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
	 * 设置：订单号
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：订单状态：101：创建订单  102：待支付 103：已支付 104:超时未支付 105：确认收单 106：异常订单反馈 201：确认收单 202:取消收单-退款 203:开始派单
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：订单状态：101：创建订单  102：待支付 103：已支付 104:超时未支付 105：确认收单 106：异常订单反馈 201：确认收单 202:取消收单-退款 203:开始派单
	 */
	public Integer getOrderState() {
		return orderState;
	}
	/**
	 * 设置：订单总额
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	/**
	 * 获取：订单总额
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}
	/**
	 * 设置：支付金额
	 */
	public void setOrderPay(BigDecimal orderPay) {
		this.orderPay = orderPay;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getOrderPay() {
		return orderPay;
	}
	/**
	 * 设置：订单优惠总额
	 */
	public void setOrderDiscountTotal(BigDecimal orderDiscountTotal) {
		this.orderDiscountTotal = orderDiscountTotal;
	}
	/**
	 * 获取：订单优惠总额
	 */
	public BigDecimal getOrderDiscountTotal() {
		return orderDiscountTotal;
	}
	/**
	 * 设置：订单商品总数量
	 */
	public void setOrderCommodityTotal(Integer orderCommodityTotal) {
		this.orderCommodityTotal = orderCommodityTotal;
	}
	/**
	 * 获取：订单商品总数量
	 */
	public Integer getOrderCommodityTotal() {
		return orderCommodityTotal;
	}
	/**
	 * 设置：订单支付类型  1:支付宝  2：微信 3：现金 
	 */
	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}
	/**
	 * 获取：订单支付类型  1:支付宝  2：微信 3：现金 
	 */
	public Integer getOrderPayType() {
		return orderPayType;
	}
	/**
	 * 设置：用餐人数
	 */
	public void setOrderMealsNum(String orderMealsNum) {
		this.orderMealsNum = orderMealsNum;
	}
	/**
	 * 获取：用餐人数
	 */
	public String getOrderMealsNum() {
		return orderMealsNum;
	}
	/**
	 * 设置：描述
	 */
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	/**
	 * 获取：描述
	 */
	public String getOrderRemark() {
		return orderRemark;
	}
	/**
	 * 设置：订单发票信息
	 */
	public void setOrderInvoice(String orderInvoice) {
		this.orderInvoice = orderInvoice;
	}
	/**
	 * 获取：订单发票信息
	 */
	public String getOrderInvoice() {
		return orderInvoice;
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
