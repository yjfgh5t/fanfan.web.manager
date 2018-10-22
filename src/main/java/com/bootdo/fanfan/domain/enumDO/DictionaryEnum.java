package com.bootdo.fanfan.domain.enumDO;
import java.util.HashMap;
import java.util.Map;

/**
 * 字典枚举
 */
public enum  DictionaryEnum {

    businessStartTime(1011,"营业开始时间"),
    businessEndTime(1012,"营业结束时间"),
    shopName(1021,"店铺名称"),
    minOrderPrice(1022,"最低起送价"),

    businessDelivery(1031,"商家配送"),
    fengNiaoDelivery(1032,"蜂鸟配送"),

    htmlVersion(9001,"html最新版本"),
    androidVersion(9002,"android最新版本"),
    showContact(9003,"是否显示联系我们"),

    customerId(9101,"商户Id"),
    deskId(9102,"客桌Id");
    private Integer key;

    private String  text;

    DictionaryEnum(Integer key,String text){
        this.text=text;
        this.key=key;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return key;
    }

    private static final Map<Integer,DictionaryEnum> keyVals =  new HashMap<>();

    static {
        // Initialize map from constant name to enum constant
        for(DictionaryEnum item : values()) {
            keyVals.put(item.key, item);
        }
    }

    public static DictionaryEnum get(Integer val){
        if(keyVals.containsKey(val))
            return keyVals.get(val);
        return DictionaryEnum.businessEndTime;
    }

}
