package com.bootdo.fanfan.vo;

import lombok.Data;
import java.util.Date;

import java.math.BigDecimal;

@Data
public class APIOrderListVO {

    private Integer id;

    private String orderNum;

    private Integer orderState;

    private String  orderStateText;

    private BigDecimal orderPay;

    private Integer commodityTotal;

    private String commoditImg;

    private String title;

    private Date createTime;

    //最后付款剩余秒数
    private Long lastPayTime;

    //支付宝支付串
    private String alipayOrderStr;
}
