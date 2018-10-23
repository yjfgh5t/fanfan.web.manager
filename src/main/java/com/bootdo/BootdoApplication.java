package com.bootdo;

import com.bootdo.fanfan.manager.TemplateMsgManager;
import com.bootdo.fanfan.manager.XGPushManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bootdo.*.dao")
@SpringBootApplication
public class BootdoApplication {

    @Autowired
    TemplateMsgManager msgManager;

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootdoApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    bootdo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
                " ______                    _   ______            \n" +
                "|_   _ \\                  / |_|_   _ `.          \n" +
                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");

        //启动任务
        TemplateMsgManager msgManager = applicationContext.getBean(TemplateMsgManager.class);
        msgManager.startThread(1);
        System.out.println("启动TemplageMsgManager成功");

        XGPushManager xgPushManager = applicationContext.getBean(XGPushManager.class);
        msgManager.startThread(1);
        System.out.println("启动XGPushManager成功");
    }
}
