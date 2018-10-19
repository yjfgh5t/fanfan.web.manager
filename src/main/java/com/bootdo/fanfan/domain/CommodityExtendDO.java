package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-19 17:00:04
 */
public class CommodityExtendDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//商品id
	private Integer commodityId;
	//商品价格
	private BigDecimal commodityPrice;
	//类型 [1:规格] ..
	private Integer type;
	//名称
	private String title;
	//创建时间
	private Date createTime;
	//是否删除 [0:否][1:是]
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
	 * 设置：商品id
	 */
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getCommodityId() {
		return commodityId;
	}
	/**
	 * 设置：商品价格
	 */
	public void setCommodityPrice(BigDecimal commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	/**
	 * 获取：商品价格
	 */
	public BigDecimal getCommodityPrice() {
		return commodityPrice;
	}
	/**
	 * 设置：类型 [1:规格] ..
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型 [1:规格] ..
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：名称
	 */
	public String getTitle() {
		return title;
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
	 * 设置：是否删除 [0:否][1:是]
	 */
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	/**
	 * 获取：是否删除 [0:否][1:是]
	 */
	public Integer getDelete() {
		return delete;
	}
}
