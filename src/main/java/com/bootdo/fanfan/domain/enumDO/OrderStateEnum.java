package com.bootdo.fanfan.domain.enumDO;

import java.util.HashMap;
import java.util.Map;

public enum  OrderStateEnum {

    userCreate(101,"创建订单"),
    userRequestPay(102,"请求支付"),
    userWaitPay(103,"待支付"),
    userPaid(104,"已支付"),
    userPayOvertime(105,"超时未支付"),
    userConfirm(106,"用户确认收单"),
    userException(107,"订单异常反馈"),

    //商家确认收单
    businessConfirm(201,"商家正在备货"),
    businessCancel(202,"商家取消订单"),
    businessDelivery(203,"正在派单"),

    orderSuccess(999,"订单完成");

    private Integer val;

    private String  text;

    OrderStateEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    private static final Map<Integer,OrderStateEnum> keyVals =  new HashMap<>();

    static {
        // Initialize map from constant name to enum constant
        for(OrderStateEnum item : values()) {
            keyVals.put(item.val, item);
        }
    }

    public static OrderStateEnum get(Integer val){
        if(keyVals.containsKey(val))
            return keyVals.get(val);
        return OrderStateEnum.orderSuccess;
    }

}
