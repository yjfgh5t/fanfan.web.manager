package com.bootdo.fanfan.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: JY
 * @date: 2018/10/31 16:44
 */
@Data
public class APICustomerRegisterVO extends APICustomerVO {

    /**
     * 手机验证码
     */
    @NotNull(message = "手机验证码不能为空")
    @Size(min = 4,max = 4,message = "手机验证码输入不正确")
    private String imgCode;
}
