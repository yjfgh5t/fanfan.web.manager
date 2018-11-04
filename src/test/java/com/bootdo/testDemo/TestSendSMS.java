package com.bootdo.testDemo;

import com.bootdo.fanfan.manager.AlismsManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JY
 * @date: 2018/10/31 13:48
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSendSMS {

    @Autowired
    AlismsManager alismsManager;

    @Test
    public void sendSMSCode(){

        boolean success = alismsManager.sendSMSCode("15821243531");

        System.out.println("发送："+success);

    }

    @Test
    public void sendSMSNotify(){

        boolean success = alismsManager.sendSMSDownloadApk("15821243531");

        System.out.println("发送："+success);

    }

}
