package com.bootdo.fanfan.domain;

import lombok.Data;

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
@Data
public class CommodityDO{
	//标识id
	private Integer id;
	//商品分类
	private Integer categoryId;
	//商品标题
	private String commodityTitle;
	//商品图片
	private String commodityImg;
	//商品原价
	private BigDecimal commodityPrice;
	//商品售价
	private BigDecimal commoditySalePrice;
	//商品描述
	private String commodityRemark;
	//每天/每餐 商品库存量
	private Integer commodityFiexNum;
	//商品单位
	private String commodityUnit;
	//1:有效 2:下架
	private Integer status;
	//创建时间
	private Date createTime;
	//创建人 用户主键
	private Integer customerId;
	//排序 值越大 越靠前
	private Integer order;
	//商品类型 1：出售 2：非卖品
	private Integer commodityType;
	//是否删除
	private Integer delete;
	//是否必点
	private Integer mustOrder;
}
