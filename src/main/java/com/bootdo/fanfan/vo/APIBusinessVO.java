package com.bootdo.fanfan.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像地址
     */
    private String picPath;

    /**
     * 店鋪Id
     */
    private Integer shopId;

    /**
     * 密码
     */
    private String password;
}
