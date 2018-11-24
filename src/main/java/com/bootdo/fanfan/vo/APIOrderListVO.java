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

    /**
     * 付款类型
     */
    private Integer orderPayType;

    /**
     * 支付类型文本
     */
    private String orderPayTypeText;

    private BigDecimal orderPay;

    private Integer commodityTotal;

    private String commoditImg;

    private String title;

    private Date createTime;

    //最后付款剩余秒数
    private Long lastPayTime;

    //支付宝支付串
    private String alipayOrderStr;

    private String orderDateNum;

    private String orderDeskNum;
}
