package com.bootdo.fanfan.manager.model.xg;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.ir.IdentNode;
import lombok.Data;

/**
 * @author: JY
 * @date: 2018/11/19 12:29
 */
@Data
public class XGResultModel {

    /**
     * 与请求包一致（如果请求包是非法json该字段为0）
     */
    private Long seq;

    /**
     * 推送id
     */
    @JSONField(name = "push_id")
    private String pushId;

    /**
     * 错误码，详细参照错误码对照表
     */
    @JSONField(name = "ret_code")
    private Integer retCode;

    /**
     * 用户指定推送环境，仅支持iOS
     * product： 生产环境
     * dev： 开发环境
     */
    private String environment;

    /**
     * 请求出错时的错误信息
     */
    @JSONField(name = "err_msg")
    private String errMsg;

    /**
     * 请求正确时，若有额外数据要返回，则结果封装在该字段的json中，若无额外数据，则可能无此字段
     */
    private String result;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
