package com.bootdo.fanfan.vo;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/12/14 14:55
 */
@Data
public class APIDeliveryVO {

    private Integer id;

    /**
     * 配送人姓名
     */
    private String name;

    /**
     * 配送人电话
     */
    private String tel;

    /**
     * 默认配送员
     */
    private Boolean isDefault;
}
