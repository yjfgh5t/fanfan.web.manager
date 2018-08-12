package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author: JY 支付宝退单实体
 * @date: 2018/8/10 17:53
 */
@Data
public class AlipayTradeRefundDTO {

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 用户的登录id
     */
    private String buyerLogonId;

    /**
     * 本次退款是否发生了资金变化 Y/N
     */
    private String fundChange;

    /**
     * 退款金额
     */
    private Integer refundFee;

    /**
     * 退款支付时间
     */
    private Date gmtRefundPay;

    /**
     * 买家在支付宝的用户id
     */
    private String buyerUserId;

    public String build(){

        return "{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"trade_no\":\"2014112611001004680073956707\"," +
                "\"refund_amount\":200.12," +
                "\"desc\":\"分账给2088101126708402\"" +
                "        }]" +
                "  }";
    }

}
