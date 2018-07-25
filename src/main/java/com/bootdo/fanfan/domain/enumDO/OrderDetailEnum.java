package com.bootdo.fanfan.domain.enumDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/7/9 11:22
 */
public enum  OrderDetailEnum {

    Commodity(1,"商品"),
    FullReduce(2,"满减"),
    Discount(3,"折扣"),
    SendCoupon(4,"送优惠券");

    private Integer val;

    private String  text;

    OrderDetailEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    private static final Map<Integer,OrderDetailEnum> keyVals =  new HashMap<>();

    static {
        // Initialize map from constant name to enum constant
        for(OrderDetailEnum item : values()) {
            keyVals.put(item.val, item);
        }
    }

    public static OrderDetailEnum get(Integer val){
        if(keyVals.containsKey(val))
            return keyVals.get(val);
        return OrderDetailEnum.Commodity;
    }

}
