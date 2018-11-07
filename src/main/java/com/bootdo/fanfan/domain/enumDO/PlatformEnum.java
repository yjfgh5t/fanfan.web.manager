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
    AlipayMiniprogram(1,"支付宝小程序","AlipayMiniprogram"),

    /**
     * 微信小程序
     */
    WechatMiniprogram(2,"微信小程序","WechatMiniprogram"),
    /**
     * Android商户端
     */
    CustomerAndroid(3,"Android商户端","CustomerAndroid");

    private Integer val;

    private String  text;

    private String code;

    public String getCode(){
        return code;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    PlatformEnum(Integer val,String text,String code){
        this.text=text;
        this.val=val;
        this.code = code;
    }

    public static PlatformEnum get(Integer val){
        for (PlatformEnum platformEnum : PlatformEnum.values()){
            if(platformEnum.val.equals(val)){
                return platformEnum;
            }
        }
        return null;
    }
}
