package com.bootdo.fanfan.manager;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.msgQueue.AbstractMsgQueue;
import com.bootdo.fanfan.manager.model.xg.XGAndroidModel;
import com.bootdo.fanfan.manager.model.xg.XGApp;
import com.bootdo.fanfan.manager.model.xg.XGMessageModel;
import com.bootdo.fanfan.manager.model.xg.XGResultModel;
import com.bootdo.fanfan.vo.model.XGPushModel;
import com.tencent.xinge.Message;
import com.tencent.xinge.Style;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: JY
 * @date: 2018/5/24 9:40
 */
@Component
public class XGPushManager extends AbstractMsgQueue<XGPushModel> {

    @Autowired
    BootdoConfig bootdoConfig;

    @Autowired
    XGApp xgApp;

    private  static Logger logger= LoggerFactory.getLogger(AbstractMsgQueue.class);


    /**
     * 消息回调
     * @param item
     */
    @Override
    protected void runCallback(XGPushModel item) {

        StringBuilder builder = new StringBuilder("开始推送信鸽消息---->\t");

        com.bootdo.fanfan.manager.model.xg.XGPushModel xgPushModel = new com.bootdo.fanfan.manager.model.xg.XGPushModel();
        xgPushModel.setAudienceType("account");
        if(item.getNotification()) {
            xgPushModel.setMessageType("notify");
        }else{
            xgPushModel.setMessageType("message");
        }
        xgPushModel.setPlatform("android");

        //推送人
        List<String> target = new ArrayList<>();
        target.add(item.getUserId()+"");
        xgPushModel.setTarget(target);

        //推送的消息主体
        XGMessageModel messageModel = new XGMessageModel();
        messageModel.setTitle(item.getMsgTitle());
        messageModel.setContent(item.getMsgContent());

        //推送android消息设置
        XGAndroidModel xgAndroidModel = new XGAndroidModel();
        xgAndroidModel.setCustomContent(item.getParams());
        messageModel.setAndroid(xgAndroidModel);

        //设置消息
        xgPushModel.setMessage(messageModel);

        XGResultModel result = xgApp.push(bootdoConfig.getXgAppId(),bootdoConfig.getXgSecretKey(), xgPushModel);

        builder.append("推送人："+item.getUserId());

        builder.append("推送结果："+result.toString());

        logger.info("信鸽推送："+builder.toString());
    }

    /**
     * 推送通知消息
     */
    private void pushNotify(){

    }
}
