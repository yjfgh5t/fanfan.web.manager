package com.bootdo.fanfan.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: JY
 * @date: 2018/9/12 17:21
 */
@Data
public class APIShopVO {

    private static final long serialVersionUID = 1L;
    //主键
    private Integer id;
    //店铺名称
    private String name;
    //营业起始时间
    private String businessStart;
    //营业结束时间
    private String businessEnd;
    //店铺头像
    private String logo;
    //店铺状态 1：正常营业  2：停止营业
    private Integer state;
    //最低订单价格
    private Float minOrderPrice;
    //店铺地址
    private String address;
    //创建时间
    private Date createTime;
    //结束时间
    private Date modifyTime;
    //商户Id
    private Integer customerId;

}
