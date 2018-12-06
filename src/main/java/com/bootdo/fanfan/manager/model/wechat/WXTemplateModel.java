package com.bootdo.fanfan.manager.model.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/12/5 10:23
 */
@Getter
@Setter
@ToString
public class WXTemplateModel {

    private String touser;

    private String templateId;

    private String page;

    private String formId;

    /**
     * 加粗的字段 列如：keyword1.DATA
     */
    private String emphasisKeyword;

    /**
     * page简介参数
     */
    private Map<String,Object> params;

    private Map<String,Value> data;

    /**
     * 添加data节点
     * @param key
     * @param val
     */
    public void addDataItem(String key,String val){

        Value valueModel = this.new Value();
        valueModel.setValue(val);
        if(data==null){
            data = new LinkedHashMap<>();
        }
        data.put(key,valueModel);
    }

    class Value{
        @Getter
        @Setter
        private String value;
    }

}
