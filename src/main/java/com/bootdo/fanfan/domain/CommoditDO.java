package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-20 16:13:33
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
	//
	private String commoditRemark;
	//每天/每餐 商品库存量
	private Integer commoditFiexNum;
	//1:有效 2:下架 3:删除
	private Integer status;
	//修改人id
	private Integer updateBy;
	//修改时间
	private Date updateTime;
	//
	private Date createTime;

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
	 * 设置：
	 */
	public void setCommoditRemark(String commoditRemark) {
		this.commoditRemark = commoditRemark;
	}
	/**
	 * 获取：
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
	 * 设置：修改人id
	 */
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：修改人id
	 */
	public Integer getUpdateBy() {
		return updateBy;
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
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
