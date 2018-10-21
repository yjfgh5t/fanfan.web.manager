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
 * @date 2018-04-01 16:27:27
 */
@Data
public class OrderDetailDO{
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
	//商品Id
	private Integer commodityId;
}
