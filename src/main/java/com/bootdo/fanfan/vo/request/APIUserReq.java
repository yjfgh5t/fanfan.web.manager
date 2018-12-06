package com.bootdo.fanfan.vo.request;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/12/5 16:58
 */
@Data
public class APIUserReq {
    //微信code
    private String code;
    //第三方应用Id
    private String tpAppId;
    //1:支付宝 2:微信
    private Integer tpType;
    //用户昵称
    private String tpNick;
    //用户头像
    private String tpIcon;
    //1:未知、2:男、3:女
    private Integer tpSex;
    //用户地址
    private String tpAddr;
    //第三方主键
    private String tpId;
    //所在省份
    private String tpProvince;
    //所在城市
    private String tpCity;

}
