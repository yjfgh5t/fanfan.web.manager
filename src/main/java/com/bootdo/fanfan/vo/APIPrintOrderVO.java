package com.bootdo.fanfan.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class APIPrintOrderVO {
    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 总金额
     */
    private float orderTotal;

    /**
     * 支付金额
     */
    private float orderPay;

    /**
     * 桌号
     */
    private String orderDeskNum;

    /**
     * 编号
     */
    private String orderDateNum;

    /**
     * 打包、堂吃、外卖
     */
    private String orderTypeText;
    /**
     * 支付类型文本
     */
    private String orderPayTypeText;
    /**
     * 备注
     */
    private String orderRemark;

    /**
     * 下单时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH;mm:ss")
    private String orderTime;

    /**
     * 商品数量
     */
    private Integer commoditySize;

    /**
     * 商品详情
     */
    private List<APIPrintOrderDetailVO> details;
}
