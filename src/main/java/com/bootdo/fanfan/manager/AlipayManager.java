package com.bootdo.fanfan.manager;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.ExtendParams;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.exception.BDException;
import com.bootdo.common.task.RefshOrderJob;
import com.bootdo.fanfan.domain.DTO.QRCodeDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import com.bootdo.fanfan.domain.TokenDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.AlipayKeyService;
import com.bootdo.fanfan.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayManager {

    private static final Logger logger = LoggerFactory.getLogger(AlipayManager.class);

    private final String authToken = null; //"201809BB4de9a2bfd3134b02a45cfe07470ebX33";

    @Autowired
    static BootdoConfig bootdoConfig;

    @Autowired
    AlipayConfig alipayConfig;

    @Autowired
    AlipayKeyService alipayKeyService;

    @Autowired
    TokenService tokenService;

    /**
     * 第三方平台授权Token
     *
     * @param code
     */
    public AlipayOpenAuthTokenAppResponse getOpenToken(String code) {

        AlipayClient alipayClient = alipayConfig.getDefaultPlatformClient();
        //请求对象
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        //当有code时 更具code获取token
        request.setBizContent("{\"grant_type\":\"authorization_code\", \"code\":\""+code+"\"}");
        //接收对象
        AlipayOpenAuthTokenAppResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response;
            } else {
                logger.error("应用授权接口 -- 失败 Token: {}  Body: {}" ,code, response.getBody());
            }
        } catch (AlipayApiException e) {
            logger.error("应用授权接口--异常 Code:{}  {}", code, e);
        }
        return null;
    }


    /**
     * 小程序用户授权Token
     *
     * @param code
     */
    public AlipaySystemOauthTokenResponse getToken(String code) {

        AlipayClient alipayClient =  alipayConfig.getDefaultClient();
        //请求对象
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        //当有code时 更具code获取token
        request.setGrantType("authorization_code");
        request.setCode(code);
        //接收对象
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return response;
            } else {
                logger.error("授权接口 -- 失败 Token: {}  Body: {}" ,code, response.getBody());
            }
        } catch (AlipayApiException e) {
            logger.error("授权接口--异常 Code:{}  {}", code, e);
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @param code
     * @return
     */
    public AlipayUserInfoShareResponse getUserInfo(String code, Integer customerId) {
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            AlipaySystemOauthTokenResponse tokenResponse = this.getToken(code);
            if (tokenResponse == null) {
                logger.error("获取用户信息--获取Token失败");
                return null;
            }

            response = alipayClient.execute(request, tokenResponse.getAccessToken());

            if (response.isSuccess()) {
                System.out.println("授权成功，用户姓名："+response.getUserName());
                return response;
            } else {
                logger.error("获取用户信息--调用失败:{}", response.getBody());
            }

        } catch (AlipayApiException e) {
            logger.error("获取用户信息-异常：{}", e);
        }
        return null;
    }

    /**
     * 小程序支付
     *
     * @param alipayDO
     * @return
     */
    public String createTradePay(OrderAlipayDO alipayDO, Integer customerId) {
        //实例化客户端
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(alipayDO.getBody());
        model.setSubject(alipayDO.getSubject());
        model.setOutTradeNo(alipayDO.getTradeNo());
        model.setTimeoutExpress(alipayDO.getTimeoutExpress());
        model.setTotalAmount(alipayDO.getTotalAmount());
        model.setProductCode(alipayDO.getProductCode());
        model.setPassbackParams(alipayDO.getPassbackParams());
        request.setBizModel(model);
        request.setNotifyUrl("http://wxcard.com.cn/api/alipay/alipayReceiver");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            logger.info("创建预付单-返回：" + response.getBody());
            if (response.isSuccess()) {
                //就是orderString 可以直接给客户端请求，无需再做处理。
                return response.getBody();
            } else {
                logger.error("创建预付单-失败 {} {}", request.getBizContent(), response.getBody());
            }
            return "";
        } catch (AlipayApiException e) {
            logger.error("创建预付单-异常：{} {}", request.getBizContent(), e);
        }
        return "";
    }

    /**
     * 第三方平台支付
     * @param alipayDO
     * @param customerId
     * @return
     */
    public String createTradePayByAuth(OrderAlipayDO alipayDO, Integer customerId) {

        //实例化客户端
        AlipayClient alipayClient =  alipayConfig.getDefaultPlatformClient();
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.create.
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(alipayDO.getBody());
        model.setSubject(alipayDO.getSubject());
        model.setOutTradeNo(alipayDO.getTradeNo());
        model.setTimeoutExpress(alipayDO.getTimeoutExpress());
        model.setTotalAmount(alipayDO.getTotalAmount());
        model.setProductCode(alipayDO.getProductCode());
        model.setPassbackParams(alipayDO.getPassbackParams());

        //request.setBizModel(model);
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + alipayDO.getTradeNo() + "\"," +
                "\"total_amount\":" + alipayDO.getTotalAmount() + "," +
                "\"subject\":\"" + alipayDO.getSubject() + "\"," +
                "\"buyer_id\":\"2088602121036890\"" +
                "}");
        try {
            //使用的是execute
            AlipayTradeCreateResponse response = alipayClient.execute(request, null, getAuthToken(customerId));
            return response.getTradeNo();//获取返回的tradeNO。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退款
     */
    public boolean tradeRefund(String orderNum, double refundAmount, Integer customerId) {
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNum + "\"," +
                "\"refund_amount\":" + refundAmount + "" +
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            //执行退款
            response = alipayClient.execute(request);
            logger.info("退款-返回：{}", response.getBody());
            if (response.isSuccess()) {
                return response.getCode().equals("10000");
            } else {
                logger.error("退款-失败 {} {}", request.getBizContent(), response.getBody());
            }
        } catch (AlipayApiException e) {
            logger.error("退款-异常：{} {}", request.getBizContent(), e);
        }

        return false;
    }

    /**
     * 创建小程序二维码
     *
     * @return
     */
    public String createQRCode(QRCodeDTO codeDTO, String authToken) {

        //实例化客户端
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayOpenAppQrcodeCreateRequest request = new AlipayOpenAppQrcodeCreateRequest();
        request.setBizContent("{" +
                "\"url_param\":\"" + codeDTO.getUrlParam() + "\"," +
                "\"query_param\":\"" + codeDTO.getQueryParamToString() + "\"," +
                "\"describe\":\"" + codeDTO.getDescribe() + "\"" +
                "  }");
        AlipayOpenAppQrcodeCreateResponse response = null;
        try {
            response = alipayClient.execute(request, null, this.authToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return response.getQrCodeUrl();
    }

    /**
     * 关闭支付交易
     *
     * @return
     */
    public boolean closeTradePay(String tradeNo, String outTradeNo, String authToken) {

        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
                "\"trade_no\":\"" + tradeNo + "\"," +
                "\"out_trade_no\":\"" + outTradeNo + "\"," +
                //"\"operator_id\":\"YX01\"" + 操作人id
                "  }");
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request, null, this.authToken);

            return response.isSuccess();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 查询交易
     *
     * @param tradeNo
     * @return
     */
    public AlipayTradeQueryResponse queryTradePay(String tradeNo, Integer customerId) {

        AlipayClient alipayClient = alipayConfig.getDefaultPlatformClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":\"" + tradeNo + "\"}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request,null,getAuthToken(customerId));
        } catch (AlipayApiException e) {
            logger.error("查询交易-异常：{} {}", request.getBizContent(), e);
        }
        return response;
    }

    /**
     * 推送支付宝模板消息
     */
    public boolean sendTemplateMsg(TemplateMsgDTO templateModel, String authToken) {
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayOpenAppMiniTemplatemessageSendRequest request = new AlipayOpenAppMiniTemplatemessageSendRequest();
        request.setBizContent(templateModel.build());
        AlipayOpenAppMiniTemplatemessageSendResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("推送支付宝模板-异常：{},{}", request.getBizContent(), e);
        }

        if (response.isSuccess()) {
            return true;
        } else {
            logger.error("推送支付宝模板-失败 {}", response.getBody());
        }

        return false;
    }

    /**
     * 验证签名
     *
     * @param params
     * @return
     */
    public boolean checkSign(Map<String, String> params) {
        try {
            String publicTBKey = alipayConfig.getPublicTBKey();
            boolean success = AlipaySignature.rsaCheckV1(params, publicTBKey, "utf-8", "RSA2");
            if (!success) {
                logger.error("支付宝验证签名-失败 TBKey:{} 参数：{}", publicTBKey, params);
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验证签名-异常 {} {}", JSON.toJSONString(params), e);
        }
        return false;
    }


    /**
     * 获取商户授权的Token
     * @param customerId
     * @return
     */
    private String getAuthToken(Integer customerId){
        TokenDO cacheModel = tokenService.getCacheModel(customerId, PlatformEnum.AlipayMiniprogram);
        if(cacheModel==null){
            throw new BDException("商户未授权");
        }

        return cacheModel.getAppToken();
    }
}
