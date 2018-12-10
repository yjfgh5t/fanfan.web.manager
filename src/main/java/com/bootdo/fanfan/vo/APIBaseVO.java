package com.bootdo.fanfan.vo;

import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import lombok.Data;

/**
 * @author: JY
 * @date: 2018/5/9 15:59
 */
@Data
public class APIBaseVO {
    /**
     * 客户端类型 PlatformEnum [Android Alipay Wechat]
     */
    private String clientType;

    /**
     * 客户端用户id
     */
    private Integer userId;

    /**
     * 店铺用户id
     */
    private Integer customerId;

    /**
     * 版本
     */
    private String version;

    /**
     * 时间
     */
    private Long time;

    public PlatformEnum getClientEnumType(){
        switch (this.clientType){
            case "AlipayMiniprogram": return PlatformEnum.AlipayMiniprogram;
            case "WechatMiniprogram": return PlatformEnum.WechatMiniprogram;
            case "Android":return PlatformEnum.CustomerAndroid;
            default: return null;
        }
    }
}
