package com.bootdo.fanfan.domain.enumDO;
import java.util.HashMap;
import java.util.Map;

/**
 * 字典枚举
 */
public enum  DictionaryEnum {
    htmlVersion(9001,"html最新版本"),
    androidVersion(9002,"android最新版本"),
    showContact(9003,"是否显示联系我们"),
    aliDYKeyId(9004,"阿里大鱼Key"),
    aliDYKeySecret(9005,"阿里大鱼Secret");
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
        return null;
    }

}
