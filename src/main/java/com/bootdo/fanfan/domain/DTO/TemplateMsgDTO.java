package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/8/10 14:32
 */
@Data
public class TemplateMsgDTO {

    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 跳转地址
     */
    private String page;

    /**
     * 发送的用户
     */
    private String toUserId;

    /**
     * formId
     */
    private String formId;

    /**
     * 字段2
     */
    private String keyword1;

    /**
     * 字段3
     */
    private String keyword2;

    /**
     * 字段4
     */
    private String keyword3;

    /**
     * 付款成功通知
     */
    public TemplateMsgDTO buildPaySuccess(Integer orderId){
        this.templateId ="ZTRhNjQ0ODhhNWE3MzY5ZWZiMjI5MWNkOTc1YmU3YWE=";
        this.page="pages/order/order-detail/order-detail?orderId="+orderId;
        return this;
    }

    /**
     * 取消订单通知
     * @return
     */
    public TemplateMsgDTO buildCancelPayOrder(Integer orderId){
        this.templateId ="NDQxMGRmMTYyMjIzYTY5YTYwYzU0MmRjOTk2N2Q0MzM=";
        this.page="pages/order/order-detail/order-detail?orderId="+orderId;
        return this;
    }


    public String build(){
        StringBuilder builder = new StringBuilder();

        builder.append("{"+
                "\"to_user_id\":\""+this.toUserId+"\","+
                "\"form_id\":\""+this.formId+"\","+
                "\"user_template_id\":\""+this.templateId+"\","+
                "\"page\":\""+this.page+"\","+
                "\"data\":\"{\\\"keyword1\\\":{\\\"value\\\":\\\""+this.keyword1+"\\\"},\\\"keyword2\\\":{\\\"value\\\":\\\""+this.keyword2+"\\\"},\\\"keyword3\\\":{\\\"value\\\":\\\""+this.keyword3+"\\\"}}\""+
                "}");

        return builder.toString();
    }
}
