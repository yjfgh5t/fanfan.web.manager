package com.bootdo.fanfan.web.h5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: JY
 * @date: 2018/11/9 10:22
 */
@Controller
@RequestMapping("/h5")
public class HomeController {

    /**
     * 下载页面
     * @return
     */
    @GetMapping("download")
    public String download(){
        return "h5/download";
    }


    /**
     * 授权成功页面
     * @return
     */
    @GetMapping("authorize")
    public String authorize(){
        return "h5/authorize";
    }

    /**
     * 扫码页面
     * @return
     */
    @GetMapping("qrcode")
    public String qrcode(){
            return "h5/qrcode";
    }
}
