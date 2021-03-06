package com.bootdo.fanfan.domain.enumDO;

import java.util.HashMap;
import java.util.Map;

public enum  OrderStateEnum {
    @Deprecated
    userCreate(101,"临时订单"),
    userPostOrder(102,"提交订单"),
    userWaitPay(103,"待付款"),
    userPaid(104,"已付款"),
    userPayOvertime(105,"超时未付款"),
    userCancel(106,"用户已取消"),
    userConfirm(107,"确认收单"),
    userException(108,"订单异常"),

    //商家确认收单
    businessPending(200,"商家确认中"),
    businessConfirm(201,"商家已确认"),
    businessCancel(202,"商家已取消"),
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
