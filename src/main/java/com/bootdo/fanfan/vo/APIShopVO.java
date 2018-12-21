package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: JY
 * @date: 2018/9/12 17:21
 */
@Data
public class APIShopVO {

    private static final long serialVersionUID = 1L;
    //主键
    private Integer id;
    //店铺名称
    private String name;
    //营业起始时间
    private String businessStart;
    //营业结束时间
    private String businessEnd;
    //店铺头像
    private String logo;
    //店铺状态 1：正常营业  2：停止营业
    private Integer state;
    //最低订单价格
    private Float minOrderPrice;
    //店铺地址
    private String address;
    //商户Id
    private Integer customerId;
    //是否开启支付宝
    private Boolean alipay;
    //是否开启微信
    private Boolean wechat;
    //是否线下支付
    private Boolean offline;
    //精度
    private String lat;
    //维度
    private String lng;
    //商家联系电话
    private String telephone;
    //是否开启外卖
    private Integer takeout;
    //配送范围
    private Double deliveryRange;
    //配送费
    private BigDecimal deliveryCost;
}
