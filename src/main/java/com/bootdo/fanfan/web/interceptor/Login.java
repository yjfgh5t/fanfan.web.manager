package com.bootdo.fanfan.web.interceptor;

import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Login {
    /**
     * 是否验证登录
     * @return
     */
    boolean require() default true;

    /**
     * 访问权限
     * @return
     */
    APIAuthorityEnum authority() default APIAuthorityEnum.All;
}
