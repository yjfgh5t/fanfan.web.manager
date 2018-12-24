package com.bootdo.fanfan.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: JY
 * @date: 2018/7/21 16:37
 */
@Data
public class APICustomerVO {
    /**
     * id
     */
    @NotNull(message = "Id不能为空")
    private Integer userId;
    /**
     * 用户昵称
     */
    private String username;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    @Size(min =2,max = 16,message = "姓名长度2-16个字符之间")
    private String name;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Size(min=11,max = 11,message = "手机号输入不正确")
    private String mobile;

    /**
     * 头像地址
     */
    private String picPath;

    /**
     * 店铺名称
     */
    @NotNull(message = "店铺名称不能为空")
    @Size(min =2,max = 16,message = "店铺名称长度2-20个字符之间")
    private String shopName;

    /**
     * 密码
     */
    private String code;
}
