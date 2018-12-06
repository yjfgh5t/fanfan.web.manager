package com.bootdo.fanfan.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.common.utils.HttpClientUtils;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.manager.model.wechat.WXJSCodeModel;
import com.bootdo.fanfan.manager.model.wechat.WXTemplateModel;
import com.bootdo.fanfan.manager.model.wechat.WXTokenModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/12/5 9:53
 */
@Component
public class WechatManager {

    Logger logger = LoggerFactory.getLogger(WechatManager.class);

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    AlipayConfig alipayConfig;

    /**
     * js code url
     */
    private final String wxCodeUrl ="https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 获取微信Token
     * @return
     */
    public String getToken(){
        //获取数据
        WXTokenModel tokenDO = (WXTokenModel) redisUtils.get(RedisConstant.WX_MINI_PROGRAM_TOKEN_KEY);

        long nowSec = System.currentTimeMillis() / 1000;

        //判断Token是否过期
        if (tokenDO == null || (nowSec + 240) >= tokenDO.getExpiresSec()) {
            try {
                //拼接参数
                Map<String,Object> params = new HashMap<>();
                params.put("grant_type","client_credential");
                params.put("appid",alipayConfig.getDefaultWXClient().getAppId());
                params.put("secret",alipayConfig.getDefaultWXClient().getAppSecret());
                //调用接口
                String accessToken = httpclient(alipayConfig.getDefaultWXClient().getUrlPath()+"token",params);
                if(StringUtils.isNotEmpty(accessToken)) {
                    tokenDO = JSONObject.parseObject(accessToken, WXTokenModel.class);
                    //设置过期时间
                    tokenDO.setExpiresSec(nowSec + tokenDO.getExpiresSec());
                    //更新Redis
                    redisUtils.set(RedisConstant.WX_MINI_PROGRAM_TOKEN_KEY, tokenDO);
                }
            } catch (Exception ex) {
                logger.error("刷新WX-Token失败{}", ex);
            }
        }
        return tokenDO == null ? "" : tokenDO.getTokenValue();
    }

    /**
     * 获取JsCode
     * @param code
     * @return
     */
    public WXJSCodeModel getJsCodeModel(String code){

        Map<String,String> params = new HashMap<>();
        params.put("appid", alipayConfig.getDefaultWXClient().getAppId());
        params.put("secret", alipayConfig.getDefaultWXClient().getAppSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        //调用接口
        String response = HttpClientUtils.doGet(wxCodeUrl, params);

        WXJSCodeModel result = JSON.parseObject(response, WXJSCodeModel.class);
        result.setAppId(alipayConfig.getDefaultWXClient().getAppId());

        return result;
    }

    /**
     * 发送模板消息
     * @param templateSendModel
     * @return
     */
    public boolean sendTemplate(WXTemplateModel templateSendModel){
        Map<String, Object> params = new HashMap<>();
        params.put("touser", templateSendModel.getTouser());
        params.put("template_id", templateSendModel.getTemplateId());

        //跳转参数
        String page = templateSendModel.getPage();
        if (templateSendModel.getParams() != null) {
            page += page.indexOf('?') == -1 ? "?" : "&";
            page += "templateParams=" + JSONObject.toJSONString(templateSendModel.getParams());
        }
        params.put("page", page);
        params.put("form_id", templateSendModel.getFormId());
        params.put("data", templateSendModel.getData());

        //加粗字段
        if (StringUtils.isNotEmpty(templateSendModel.getEmphasisKeyword())) {
            params.put("emphasis_keyword", templateSendModel.getEmphasisKeyword());
        }

        String result = httpclient(alipayConfig.getDefaultWXClient().getUrlPath()+"message/wxopen/template/send?access_token=" + getToken(), params);

        if (result != null) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getInteger("errcode") == 0) {
                return true;
            }
        }

        logger.error("发送模板消息失败请求参数：{} 返回：{}", params, result);
        return false;
    }


    //region 私有方法

    private String httpclient(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            if (params != null) {
                StringEntity paramsEntity = new StringEntity(JSONObject.toJSONString(params), "utf-8");
                post.setEntity(paramsEntity);
            }
            post.setHeader("Content-Type", "application/json;encoding=utf-8");
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            return string;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.info("调用接口失败：{}", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("调用接口失败：{}", e);
        }

        return null;
    }

    //endregion

}
