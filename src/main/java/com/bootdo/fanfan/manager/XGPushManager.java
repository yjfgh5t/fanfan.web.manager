package com.bootdo.fanfan.manager;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.msgQueue.AbstractMsgQueue;
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

/**
 * @author: JY
 * @date: 2018/5/24 9:40
 */
@Component
public class XGPushManager extends AbstractMsgQueue<XGPushModel> {

    @Autowired
    BootdoConfig bootdoConfig;

    XingeApp xingeApp;

    private  static Logger logger= LoggerFactory.getLogger(AbstractMsgQueue.class);

    /**
     * 初始化
     */
    @PostConstruct
    private void init(){
         xingeApp = new XingeApp(bootdoConfig.getXgAccessId(),bootdoConfig.getXgSecretKey());
    }

    /**
     * 消息回调
     * @param item
     */
    @Override
    protected void runCallback(XGPushModel item) {

        StringBuilder builder = new StringBuilder("开始推送信鸽消息---->\t");

        Message message = new Message();

        message.setContent(item.getMsgContent());
        message.setTitle(item.getMsgTitle());
        message.setMultiPkg(1);
        message.setCustom(item.getParams());
        //消息类型
        message.setType(item.getNotification()?Message.TYPE_NOTIFICATION:Message.TYPE_MESSAGE);
        //通知样式
        if(item.getNotification()){
            //通知样式
            Style style = new Style(0,0,1,1,0);
            message.setStyle(style);
        }

        builder.append("推送人："+item.getUserId());

        JSONObject jsonObject = xingeApp.pushSingleAccount(0,item.getUserId().toString(),message);

        builder.append("推送结果："+jsonObject.toString());

        logger.info("信鸽推送："+builder.toString());
    }

    /**
     * 推送通知消息
     */
    private void pushNotify(){

    }
}
