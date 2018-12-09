package com.bootdo.fanfan.vo;

import lombok.Data;

@Data
public class APIUserVO {
    //主键
    private Integer id;
    //用户id主键
    private Integer userId;
    //1:支付宝 2:微信
    private Integer tpType;
    //用户昵称
    private String tpNick;
    //用户头像
    private String tpIcon;
    //1:未知、2:男、3:女
    private Integer tpSex;
}
