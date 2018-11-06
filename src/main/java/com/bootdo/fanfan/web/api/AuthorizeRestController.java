package com.bootdo.fanfan.web.api;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/authorize")
public class AuthorizeRestController extends ApiBaseRestController{

    @Autowired
    BootdoConfig bootdoConfig;

    /**
     * 获取用户认证信息
     * @return
     */
    @GetMapping("/")
    public R get(){

        return R.ok();
    }

    @GetMapping("/url")
    public R getUrl(){
        return R.ok().put("data", bootdoConfig.getAuthorizeUrl());
    }

}
