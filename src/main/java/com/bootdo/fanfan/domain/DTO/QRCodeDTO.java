package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/7/25 16:49
 */
@Data
public class QRCodeDTO {

    /**
     * 跳转的页面地址
     */
    private String urlParam;

    /**
     * 参数
     */
    private Map<String,Object> queryParam;

    /**
     * 描述
     */
    private String describe;

    /**
     * 添加
     * @param key
     * @param val
     */
    public void putParam(String key,Object val){
        if(queryParam==null){
            queryParam = new HashMap<>();
        }
        queryParam.put(key,val);
    }

    /**
     * 获取ToString
     * @return
     */
    public String getQueryParamToString(){
        if(queryParam==null || queryParam.size()==0){
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder("");

        queryParam.forEach((key,val)->{
            stringBuilder.append(key+"="+val+"&");
        });

        return stringBuilder.substring(0,stringBuilder.length()-2);
    }

}
