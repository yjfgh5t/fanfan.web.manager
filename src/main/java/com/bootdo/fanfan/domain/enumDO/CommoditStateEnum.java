package com.bootdo.fanfan.domain.enumDO;

import java.util.HashMap;
import java.util.Map;

public enum CommoditStateEnum {

    useful(1,"有效"),
    shelves(2,"下架"),
    delete(3,"删除");
    private Integer key;

    private String  text;

    CommoditStateEnum(Integer key,String text){
        this.text=text;
        this.key=key;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return key;
    }

    private static final Map<Integer,CommoditStateEnum> keyVals =  new HashMap<>();

    static {
        // Initialize map from constant name to enum constant
        for(CommoditStateEnum item : values()) {
            keyVals.put(item.key, item);
        }
    }

    public static CommoditStateEnum get(Integer val){
        if(keyVals.containsKey(val))
            return keyVals.get(val);
        return CommoditStateEnum.useful;
    }

}
