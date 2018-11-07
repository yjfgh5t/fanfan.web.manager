package com.bootdo.fanfan.web.api;

import com.bootdo.common.utils.R;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.vo.model.XGPushModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * @author: JY
 * @date: 2018/11/7 13:01
 */
@RestController
@RequestMapping("api/debug")
public class DebugRestController {

    @Autowired
    XGPushManager xgPushManager;

    @GetMapping("pushNotify")
    public R pushNotify(Integer customerId,String message){

        XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.authorizeNotify,customerId.longValue());
        pushModel.setMsgTitle(message);
        pushModel.setMsgContent("支付宝授权成功");
        pushModel.setNotification(true);
        pushModel.addParams("data", "success");
        pushModel.addParams("time", Calendar.getInstance().getTime());
        //推送消息
        xgPushManager.put(pushModel);

        return R.ok();
    }
}
