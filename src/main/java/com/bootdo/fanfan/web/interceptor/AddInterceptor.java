package com.bootdo.fanfan.web.interceptor;

import com.bootdo.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter{

    @Autowired
    RedisUtils redisUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new APILoginInterceptor(redisUtils))
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/alipay/**","/api/info/**");
    }

}
