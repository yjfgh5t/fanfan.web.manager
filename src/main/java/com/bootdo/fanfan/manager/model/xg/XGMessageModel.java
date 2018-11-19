package com.bootdo.fanfan.manager.model.xg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author: JY
 * @date: 2018/11/19 11:13
 */
@Data
public class XGMessageModel {
    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 富媒体元素地址，建议小于5个
     */
    private List<String> xgMediaResources;

    /**
     * Android
     */
    private XGAndroidModel android;

    /**
     * IOS
     */
    private XGIOSModel ios;

    /**
     * 消息将在哪些时间段允许推送给用户，建议小于10个，不能为空
     */
    @JSONField(name = "accept_time")
    private List<XGAcceptTimeModel> acceptTime;
}
