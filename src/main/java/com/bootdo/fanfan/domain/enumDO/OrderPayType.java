package com.bootdo.fanfan.domain.enumDO;

import lombok.Getter;

/**
 * @author: JY
 * @date: 2018/8/28 19:03
 */
public enum OrderPayType {

    Alipay(1,"支付宝"),
    Wechat(2,"微信"),
    Cash(3,"现金");

    @Getter
    private Integer id;

    @Getter
    private String  text;

    OrderPayType(Integer val,String text){
        this.text=text;
        this.id=val;
    }

}
