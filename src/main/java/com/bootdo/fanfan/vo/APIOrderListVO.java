package com.bootdo.fanfan.vo;

import lombok.Data;
import java.util.Date;

import java.math.BigDecimal;

@Data
public class APIOrderListVO {

    private String orderNum;

    private Integer orderState;

    private String  orderStateText;

    private BigDecimal orderPay;

    private Integer commodityTotal;

    private String commoditImg;

    private String title;

    private Date createTime;
}
