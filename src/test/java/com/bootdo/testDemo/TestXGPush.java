package com.bootdo.testDemo;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.fanfan.manager.model.xg.*;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: JY
 * @date: 2018/11/19 10:32
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestXGPush {

    @Autowired
    BootdoConfig bootdoConfig;

    @Autowired
    XGApp xgApp;

    @Test
    public void pushAndroid(){

        XGPushModel xgPushModel = new XGPushModel();
        xgPushModel.setAudienceType("account");
        xgPushModel.setMessageType("notify");
        xgPushModel.setPlatform("android");

        List<String> target = new ArrayList<>();
        target.add("132");

        xgPushModel.setTarget(target);

        XGMessageModel messageModel = new XGMessageModel();
        messageModel.setTitle("测试推送标题。。");
        messageModel.setContent("测试推送内容。。");
        messageModel.setAndroid(new XGAndroidModel());

        //设置消息
        xgPushModel.setMessage(messageModel);


        XGResultModel result = xgApp.push(bootdoConfig.getXgAppId(),bootdoConfig.getXgSecretKey(), xgPushModel);

        System.out.println("推送返回"+ result.toString());
    }

}
