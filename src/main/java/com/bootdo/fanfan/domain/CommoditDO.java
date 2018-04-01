package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-30 22:53:17
 */
public class CommoditDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//标识id
	private Integer id;
	//商品标题
	private String commoditTitle;
	//商品图片
	private String commoditImg;
	//商品原价
	private BigDecimal commoditPrice;
	//商品售价
	private BigDecimal commoditSalePrice;
	//商品描述
	private String commoditRemark;
	//每天/每餐 商品库存量
	private Integer commoditFiexNum;
	//1:有效 2:下架
	private Integer status;
	//创建时间
	private Date createTime;
	//创建人 用户主键
	private Integer createId;
	//排序 值越大 越靠前
	private Integer order;
	//商品类型 1：出售 2：非卖品
	private Integer commoditType;
	//是否删除
	private Integer delete;

	/**
	 * 设置：标识id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：标识id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：商品标题
	 */
	public void setCommoditTitle(String commoditTitle) {
		this.commoditTitle = commoditTitle;
	}
	/**
	 * 获取：商品标题
	 */
	public String getCommoditTitle() {
		return commoditTitle;
	}
	/**
	 * 设置：商品图片
	 */
	public void setCommoditImg(String commoditImg) {
		this.commoditImg = commoditImg;
	}
	/**
	 * 获取：商品图片
	 */
	public String getCommoditImg() {
		return commoditImg;
	}
	/**
	 * 设置：商品原价
	 */
	public void setCommoditPrice(BigDecimal commoditPrice) {
		this.commoditPrice = commoditPrice;
	}
	/**
	 * 获取：商品原价
	 */
	public BigDecimal getCommoditPrice() {
		return commoditPrice;
	}
	/**
	 * 设置：商品售价
	 */
	public void setCommoditSalePrice(BigDecimal commoditSalePrice) {
		this.commoditSalePrice = commoditSalePrice;
	}
	/**
	 * 获取：商品售价
	 */
	public BigDecimal getCommoditSalePrice() {
		return commoditSalePrice;
	}
	/**
	 * 设置：商品描述
	 */
	public void setCommoditRemark(String commoditRemark) {
		this.commoditRemark = commoditRemark;
	}
	/**
	 * 获取：商品描述
	 */
	public String getCommoditRemark() {
		return commoditRemark;
	}
	/**
	 * 设置：每天/每餐 商品库存量
	 */
	public void setCommoditFiexNum(Integer commoditFiexNum) {
		this.commoditFiexNum = commoditFiexNum;
	}
	/**
	 * 获取：每天/每餐 商品库存量
	 */
	public Integer getCommoditFiexNum() {
		return commoditFiexNum;
	}
	/**
	 * 设置：1:有效 2:下架 3:删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1:有效 2:下架 3:删除
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：创建人 用户主键
	 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**
	 * 获取：创建人 用户主键
	 */
	public Integer getCreateId() {
		return createId;
	}
	/**
	 * 设置：排序 值越大 越靠前
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序 值越大 越靠前
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * 设置：商品类型 1：出售 2：非卖品
	 */
	public void setCommoditType(Integer commoditType) {
		this.commoditType = commoditType;
	}
	/**
	 * 获取：商品类型 1：出售 2：非卖品
	 */
	public Integer getCommoditType() {
		return commoditType;
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
