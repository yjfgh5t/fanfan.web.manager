package com.bootdo.fanfan.manager;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: JY阿里
 * @date: 2018/10/31 10:48
 */
@Component
public class AlismsManager {

    private static final Logger logger = LoggerFactory.getLogger(AlismsManager.class);

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    RedisUtils redisUtils;

    @Bean
    private IAcsClient getClient() throws ClientException {
        //查询数据
        Map<Integer, String> dictionary = dictionaryService.queryByKeys(1, DictionaryEnum.aliDYKeyId.getVal(), DictionaryEnum.aliDYKeySecret.getVal());

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", dictionary.get(DictionaryEnum.aliDYKeyId.getVal()), dictionary.get(DictionaryEnum.aliDYKeySecret.getVal()));
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        return new DefaultAcsClient(profile);
    }

    /**
     * 发送短信验证码
     * @return
     */
    public boolean sendSMSCode(String mobile) {
        try {
            Map<String,String> params = new HashMap<>();
            String code = 1000+new Random().nextInt(999)+"";
            params.put("code",code);
            //组装请求对象
            SendSmsRequest request = getSendSmsRequest(mobile,params);
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode("SMS_149920014");
            //请求失败这里会抛ClientException异常
            //执行发送
            SendSmsResponse sendSmsResponse =null;
            if(1==2){
                sendSmsResponse = new SendSmsResponse();
                sendSmsResponse.setCode("OK");
            }else{
                sendSmsResponse =getClient().getAcsResponse(request);
            }
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //将二维码放入缓存
                redisUtils.hset(RedisConstant.MOBILE_CODE_KEY,mobile,code,60*5);
                return true;
            }
            logger.warn("发送短信验证码失败 手机号：{} {}", mobile, JSON.toJSONString(sendSmsResponse));

        } catch (ClientException ex) {
            logger.error("发送短信验证码异常 手机号：{}, 异常：{}", mobile, ex);
        }
        return false;
    }

    /**
     * 验证
     * @param mobile
     * @param code
     * @return
     */
    public boolean checkCode(String mobile,String code){
        String strCode = (String) redisUtils.hget(RedisConstant.MOBILE_CODE_KEY, mobile);

        boolean success = code.equals(strCode);

        //验证成功需删除
        if(success){
            redisUtils.hdel(RedisConstant.MOBILE_CODE_KEY,mobile);
        }

        return success;
    }

    /**
     * 发送下载APK地址
     * @return
     */
    public boolean sendSMSDownloadApk(String mobile) {
        try {
            Map<String,String> params = new HashMap<>();
            //组装请求对象
            SendSmsRequest request = getSendSmsRequest(mobile,params);
            //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
            request.setTemplateCode("SMS_150740039");
            //执行发送
            SendSmsResponse sendSmsResponse =null;
            if(1==2){
                sendSmsResponse = new SendSmsResponse();
                sendSmsResponse.setCode("OK");
            }else{
                sendSmsResponse =getClient().getAcsResponse(request);
            }

            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return true;
            }
            logger.warn("发送短信通知下载APK失败 手机号：{} {}", mobile, JSON.toJSONString(sendSmsResponse));

        } catch (ClientException ex) {
            logger.error("发送短信通知下载APK异常 手机号：{}, 异常：{}", mobile, ex);
        }
        return false;
    }

    /**
     * 请求封装
     * @param mobile
     * @param params
     * @return
     */
    private SendSmsRequest getSendSmsRequest(String mobile,Map<String,String> params){
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("饭饭点餐");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(JSON.toJSONString(params));

        return request;
    }

}
