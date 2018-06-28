package com.bootdo.fanfan.vo.model;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/5/24 10:51
 */
@Data
public class XGPushModel {

    /**
     * 推送用户id
     */
    private Long userId;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息标题
     */
    private String msgTitle;

    /**
     * 消息参数
     */
    @Getter
    private Map<String,Object> params;

    /**
     * 通知消息
     */
    private Boolean notification;


    /**
     * 构造函数
     * @param msgType
     * @param userId
     */
    public XGPushModel(MsgType msgType, Long userId){
        this(msgType,userId,"","");
    }

    /**
     * 构造函数
     * @param userId
     * @param msgTitle
     * @param msgContent
     */
    public XGPushModel(MsgType msgType, Long userId,String msgTitle, String msgContent){
        this.userId=userId;
        this.msgTitle = msgTitle;
        this.msgContent=msgContent;
        this.notification=true;

        if(this.params==null) {
            params = new HashMap<>();
            params.put("msgType",msgType.toString());
        }
    }

    /**
     * 添加参数
     * @param paramKey
     * @param paramVal
     * @return
     */
    public XGPushModel addParams(String paramKey,Object paramVal){

        //添加
        params.put(paramKey,paramVal);

        return this;
    }

    /**
     * 消息类型
     */
    public enum MsgType{
        /**
         * 支付订单
         */
        payOrder,
        /**
         * 签收订单
         */
        signOrder
    }
}
