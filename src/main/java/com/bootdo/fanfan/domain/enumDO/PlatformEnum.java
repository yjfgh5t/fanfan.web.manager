package com.bootdo.fanfan.domain.enumDO;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/8/10 17:22
 */
public enum PlatformEnum {

    /**
     * 支付宝小程序
     */
    AlipayMiniprogram(1,"支付宝小程序"),

    /**
     * 微信小程序
     */
    WechatMiniprogram(2,"微信小程序"),
    /**
     * Android商户端
     */
    CustomerAndroid(3,"Android商户端");

    private Integer val;

    private String  text;

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    PlatformEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }

}
