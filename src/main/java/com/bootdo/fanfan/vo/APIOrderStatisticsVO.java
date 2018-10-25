package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: JY
 * @date: 2018/10/25 11:16
 */
@Data
public class APIOrderStatisticsVO {

    /**
     * 订单总额
     */
    private BigDecimal orderTotal;

    /**
     * 订单总数量
     */
    private Integer orderCount;

    /**
     * 取消的订单总额
     */
    private BigDecimal cancelOrderTotal;

    /**
     * 取消的订单总数量
     */
    private Integer cancelOrderCount;

}
