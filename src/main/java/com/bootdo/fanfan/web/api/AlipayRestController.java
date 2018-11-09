package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.AuthorizeDO;
import com.bootdo.fanfan.domain.TokenDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.IdentificationEnum;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.AuthorizeService;
import com.bootdo.fanfan.service.TokenService;
import com.bootdo.fanfan.vo.model.XGPushModel;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping(value = "/api/alipay")
public class AlipayRestController {

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    EMapper eMapper;

    @Autowired
    AlipayRecordService alipayRecordService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    XGPushManager xgPushManager;

    Log log = LogFactory.getLog(AlipayRestController.class);

    /**
     * 接收支付宝付款单消息
     * @return
     */
    @RequestMapping("/alipayReceiver")
    public String alipayReceiver(HttpServletRequest request){

        //获取参数
        Map<String,String> mapParams = new HashMap<>(request.getParameterMap().size()),convertParams = new HashMap<>(request.getParameterMap().size());
 
         //数据转换
         request.getParameterMap().forEach((key,val)->{
             String valueStr = "";
             for (int i = 0; i < val.length; i++) {
                 valueStr = (i == val.length - 1) ? valueStr + val[i]: valueStr + val[i] + ",";
             }
             mapParams.put(key,valueStr);
             convertParams.put(StringUtils.lineToHump(key),valueStr);
         });

       if(!alipayManager.checkSign(mapParams)) {
           return "failure";
       }

       //转换
       AlipayRecordDO recordDO = eMapper.map(convertParams, AlipayRecordDO.class);

       //执行保存
        try {
            //修改订单支付状态
            alipayRecordService.save(recordDO);
        }catch (Exception ex){
            log.error("保存支付宝异步消息异常--{}",ex);
        }

        return "success";
    }

    /**
     * 支付宝用户授权
     * @return
     */
    @RequestMapping("authRedirect")
    public void authRedirect(@RequestParam("app_id") String appId, @RequestParam("app_auth_code") String appAuthCode, @RequestParam("customer_id") Integer customerId,HttpServletResponse response) throws ParseException, IOException {
        TokenDO tokenDO = new TokenDO();
        tokenDO.setCustomerId(customerId);
        AlipayOpenAuthTokenAppResponse tokenResponse = alipayManager.getTokenPlatform(appAuthCode);

        if(tokenResponse!=null){
            tokenDO.setAppExpries(DateUtils.parseDate("2100-01-01","yyyy-MM-dd"));
            tokenDO.setAppId(appId);
            tokenDO.setAppUserId(tokenResponse.getAuthAppId());
            tokenDO.setAppRefreshToken(tokenResponse.getAppRefreshToken());
            tokenDO.setAppToken(tokenResponse.getAppAuthToken());
            tokenDO.setAppUserId(tokenResponse.getUserId());
            tokenDO.setPlatformType(PlatformEnum.AlipayMiniprogram.getVal());
            //保存Token
            tokenService.save(tokenDO);

            //设置商户授权成功
            AuthorizeDO authorizeDO = new AuthorizeDO();
            authorizeDO.setCustomerId(customerId);
            authorizeDO.setAuthorizeState(BooleanEnum.True.getVal());
            //如用户重新授权 需重置认证状态
            authorizeDO.setIdentificationState(IdentificationEnum.NoIdentification.getVal());
            authorizeDO.setPayeeId("");
            authorizeDO.setPayeeName("");
            //保存授权信息
            int success  = authorizeService.save(authorizeDO);

            //推送授权成功消息
            if(success>0){
                XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.authorizeNotify, customerId.longValue());
                pushModel.setMsgTitle("授权通知");
                pushModel.setMsgContent("支付宝授权成功");
                pushModel.setNotification(true);
                pushModel.addParams("data", "success");
                pushModel.addParams("time", Calendar.getInstance().getTime());
                //推送消息
                xgPushManager.put(pushModel);
            }
        }
        //跳转授权成功页面
        response.sendRedirect("/h5/authorize");
    }
}
