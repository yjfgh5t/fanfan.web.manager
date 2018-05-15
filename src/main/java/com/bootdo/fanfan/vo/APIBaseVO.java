package com.bootdo.fanfan.vo;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/5/9 15:59
 */
@Data
public class APIBaseVO {

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 支付宝用户id
     */
    private String userId;

    /**
     * 店铺用户id
     */
    private Integer customerId;

}
