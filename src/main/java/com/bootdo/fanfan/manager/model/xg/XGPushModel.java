package com.bootdo.fanfan.manager.model.xg;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


/**
 * @author: JY
 * @date: 2018/11/19 11:12
 */
@Data
public class XGPushModel {

    /**
     * 推送目标
     * 1）all：全量推送
     * 2）tag：标签推送
     * 3）token：单设备推送
     * 4）token_list：设备列表推送
     * 5）account：单账号推送
     * 6）account_list：账号列表推送
     */
    @JSONField(name = "audience_type")
    private String audienceType;

    /**
     * 客户端平台类型
     * 1）android：安卓
     * 2）ios：苹果
     * 3）all：安卓&&苹果，仅支持全量推送和标签推送（预留参数，暂不可用）
     */
    private String platform;

    /**
     * 消息体
     */
    private XGMessageModel message;

    /**
     * 消息类型
     * 1）notify：通知
     * 2）message：透传消息/静默消息
     */
    @JSONField(name = "message_type")
    private String messageType;

    /**
     * 推送的目标
     */
    @JsonIgnore
    private List<String> target;
}
