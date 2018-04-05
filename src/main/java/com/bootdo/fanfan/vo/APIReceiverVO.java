package com.bootdo.fanfan.vo;

import lombok.Data;

@Data
public class APIReceiverVO {
    //主键
    private Integer id;
    //用户主键
    private Integer userId;
    //收货人电话
    private String tel;
    //收货人名称
    private String name;
    //收货人性别
    private String sex;
    //区域
    private String district;
    //街道
    private String street;
    //详细地址
    private String detail;
    //精度
    private String lat;
    //纬度
    private String lng;
}
