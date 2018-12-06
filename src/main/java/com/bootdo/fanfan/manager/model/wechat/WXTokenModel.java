package com.bootdo.fanfan.manager.model.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: JY
 * @date: 2018/12/5 9:57
 */
@Data
public class WXTokenModel {

    @JSONField(name="access_token")
    private String tokenValue;

    @JSONField(name = "expires_in")
    private Long expiresSec;
}
