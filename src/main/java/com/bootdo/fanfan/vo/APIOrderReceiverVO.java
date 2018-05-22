package com.bootdo.fanfan.vo;

import lombok.Data;

@Data
public class APIOrderReceiverVO {
    //订单主键
    private Integer id;
    //收货人电话
    private String tel;
    //收货人名称
    private String name;
    //收货人性别
    private String sex;
    //区域
    private String addr;
    //详细地址
    private String addrDetail;
    //精度
    private String lat;
    //纬度
    private String lng;
    //配送人
    private Integer deliveryId;
    //配送方式
    private String  deliveryType;
    //配送人信息
    private APIOrderDeliveryVO  deliveryVO;
}
