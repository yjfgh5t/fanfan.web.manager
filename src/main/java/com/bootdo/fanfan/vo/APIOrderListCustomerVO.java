package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: JY
 * @date: 2018/6/29 14:51
 */
@Data
public class APIOrderListCustomerVO {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 订单状态
     */
    private Integer orderState;

    /**
     * 订单状态文本
     */
    private String  orderStateText;

    /**
     * 订单金额
     */
    private BigDecimal orderPay;

    /**
     * 订单桌号
     */
    private String orderDeskNum;

    /**
     * 当日串编号
     */
    private String orderDateNum;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 订单详情列表
     */
    private List<APIOrderDetailVO> details;

    /**
     * 商品数量
     */
    private Integer commoditySize;
}
