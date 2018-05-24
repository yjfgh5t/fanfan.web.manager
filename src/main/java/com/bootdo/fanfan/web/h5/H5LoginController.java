package com.bootdo.fanfan.web.h5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: JY
 * @date: 2018/5/24 14:59
 */
@Controller
@RequestMapping("/h5/login")
public class H5LoginController {

    @RequestMapping("/")
    public String Login(){
        return "h5/login";
    }

}
