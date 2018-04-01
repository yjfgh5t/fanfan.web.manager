package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-01 16:27:27
 */
public class OrderDetialDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//订单主键
	private Integer orderId;
	//商品id/活动id
	private Integer outId;
	//商品标题/活动标题
	private String outTitle;
	//商品销售价格/活动优惠价格
	private BigDecimal outPrice;
	//商品数量
	private Integer outSize;
	// 1:满减  2:折扣 3:送优惠券
	private Integer outType;

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
	 * 设置：商品id/活动id
	 */
	public void setOutId(Integer outId) {
		this.outId = outId;
	}
	/**
	 * 获取：商品id/活动id
	 */
	public Integer getOutId() {
		return outId;
	}
	/**
	 * 设置：商品标题/活动标题
	 */
	public void setOutTitle(String outTitle) {
		this.outTitle = outTitle;
	}
	/**
	 * 获取：商品标题/活动标题
	 */
	public String getOutTitle() {
		return outTitle;
	}
	/**
	 * 设置：商品销售价格/活动优惠价格
	 */
	public void setOutPrice(BigDecimal outPrice) {
		this.outPrice = outPrice;
	}
	/**
	 * 获取：商品销售价格/活动优惠价格
	 */
	public BigDecimal getOutPrice() {
		return outPrice;
	}
	/**
	 * 设置：商品数量
	 */
	public void setOutSize(Integer outSize) {
		this.outSize = outSize;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getOutSize() {
		return outSize;
	}
	/**
	 * 设置： 1:满减  2:折扣 3:送优惠券
	 */
	public void setOutType(Integer outType) {
		this.outType = outType;
	}
	/**
	 * 获取： 1:满减  2:折扣 3:送优惠券
	 */
	public Integer getOutType() {
		return outType;
	}
}
