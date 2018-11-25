package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/8/10 15:46
 */
@Data
public class TemplateMsgMQDTO {

    /**
     * 模板类型 1:支付订单消息 2:退单消息
     */
    private Integer templateType;

    /**
     * 订单id
     */
    private Integer orderId;


    public TemplateMsgMQDTO buildOrderPayMQ(Integer orderId){
        this.templateType=1;
        this.orderId=orderId;
        return this;
    }

    public TemplateMsgMQDTO buildOrderRefundMQ(Integer orderId){
        this.templateType=2;
        this.orderId=orderId;
        return this;
    }

    public TemplateMsgMQDTO buildOrderCancelMQ(Integer orderId){
        this.templateType=3;
        this.orderId=orderId;
        return this;
    }

}
