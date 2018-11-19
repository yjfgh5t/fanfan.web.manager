package com.bootdo.fanfan.manager.model.xg;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.HttpClientUtils;
import com.bootdo.common.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/11/19 11:11
 */
@Component
public class XGApp {

    private String XG_PUTH_URL ="https://openapi.xg.qq.com/v3/push/app";

    /**
     * 推送android
     * @param model
     * @return
     */
    public XGResultModel push(String appId,String secretKey, XGPushModel model){

        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(model);
        //设置推送的账号数据
        jsonObject.put(model.getAudienceType()+"_list",model.getTarget());

        Map<String,String> header = new HashMap<>();
        String encryptVal = Base64Utils.encodeToString((appId+":"+secretKey).getBytes());
        header.put("Authorization", "Basic "+encryptVal);

        String response = HttpClientUtils.doPostJson(XG_PUTH_URL, jsonObject.toJSONString(),header);

        XGResultModel result =null;

        if(!StringUtils.isEmpty(response) && response.indexOf("{")==0){
            result = ((JSONObject) JSONObject.parse(response)).toJavaObject(XGResultModel.class);
        }else {
            result = new XGResultModel();
            result.setRetCode(-1);
            result.setErrMsg(response);
        }

        return result;
    }


}
