package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: JY
 * @date: 2018/5/17 11:20
 */
@Data
public class OrderDTO {

    //主键
    private Integer id;
    //订单号
    private String orderNum;
    //订单状态
    private Integer orderState;
    //订单类型
    private Integer orderType;
    //订单描述
    private String orderStateText;
    //订单总额
    private BigDecimal orderTotal;
    //支付金额
    private BigDecimal orderPay;
    //订单优惠总额
    private BigDecimal orderDiscountTotal;
    //订单商品总数量
    private Integer orderCommodityTotal;
    //订单支付类型  1:支付宝  2：微信 3：现金
    private Integer orderPayType;
    //用餐人数
    private String orderMealsNum;
    //描述
    private String orderRemark;
    //订单发票信息
    private String orderInvoice;
    //用户主键
    private String createName;
    //创建时间
    private Date createTime;

}
