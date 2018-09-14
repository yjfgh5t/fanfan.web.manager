package com.bootdo.fanfan.vo;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/7/21 16:37
 */
@Data
public class APIBusinessVO {
    /**
     * id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String picPath;

    /**
     * 店鋪Id
     */
    private Integer shopId;
}
