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
import com.bootdo.common.task.RefshOrderJob;
import com.bootdo.fanfan.domain.DTO.QRCodeDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import com.bootdo.fanfan.service.AlipayKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayManager {

    private static final Logger logger = LoggerFactory.getLogger(RefshOrderJob.class);

    private  final String authToken=null;//"201809BB4de9a2bfd3134b02a45cfe07470ebX33";

    @Autowired
    static BootdoConfig bootdoConfig;

    @Autowired
    AlipayConfig alipayConfig;

    @Autowired
    AlipayKeyService alipayKeyService;

    /**
     * 获取用信息
     * @param code
     */
    public String getToken(String code,String authToken){

        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        //请求对象
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        //当有code时 更具code获取token
        request.setGrantType("authorization_code");
        request.setCode(code);
        //接收对象
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request,null,this.authToken);
            if(response.isSuccess()){
                return response.getAccessToken();
            }else{
                logger.error("调用接口失败："+ response.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取用户信息
     * @param code
     * @return
     */
    public AlipayUserInfoShareResponse getUserInfo(String  code,String authToken){
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;

        try {
            String _token  =  this.getToken(code,this.authToken);
            if(_token==null){
                logger.error("获取用户信息--获取Token失败");
                return null;
            }

            response = alipayClient.execute(request,_token,this.authToken);

            if(response.isSuccess()){
                System.out.println(response.getUserName());
                return response;
            } else {
                logger.error("获取用户信息--调用失败:{}",response);
            }

        } catch (AlipayApiException e) {
            logger.error("获取用户信息-异常：{}",e);
        }
        return null;
    }

    /**
     * 创建支付预付单
     * @param alipayDO
     * @return
     */
    public String  createTradePay(OrderAlipayDO alipayDO,Integer customerId){
        //实例化客户端
        AlipayClient alipayClient = alipayConfig.getCustomerClient(customerId);
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
            logger.info("创建预付单-返回："+response.getBody());
            if(response.isSuccess()) {
                //就是orderString 可以直接给客户端请求，无需再做处理。
                return response.getBody();
            }else{
                logger.error("创建预付单-失败 {} {}",request.getBizContent(),response.getBody());
            }
            return "";
        } catch (AlipayApiException e) {
            logger.error("创建预付单-异常：{} {}",request.getBizContent(), e);
        }
        return "";
    }

    /**
     * 退款
     */
    public boolean tradeRefund(String orderNum,String refundAmount,Integer customerId){
        AlipayClient alipayClient = alipayConfig.getCustomerClient(customerId);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+orderNum+"\"," +
                "\"refund_amount\":"+refundAmount+"" +
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            //执行退款
            response = alipayClient.execute(request);
            logger.info("退款-返回：{}",response);
            if(response.isSuccess()){
                return response.getCode().equals("10000");
            }else{
                logger.error("退款-失败 {} {}",request.getBizContent(),response.getBody());
            }
        } catch (AlipayApiException e) {
            logger.error("退款-异常：{} {}",request.getBizContent(),e);
        }

        return false;
    }

    /**
     * 创建小程序二维码
     * @return
     */
    public String createQRCode(QRCodeDTO codeDTO,String authToken){

        //实例化客户端
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayOpenAppQrcodeCreateRequest request = new AlipayOpenAppQrcodeCreateRequest();
        request.setBizContent("{" +
                "\"url_param\":\""+codeDTO.getUrlParam()+"\"," +
                "\"query_param\":\""+codeDTO.getQueryParamToString()+"\"," +
                "\"describe\":\""+codeDTO.getDescribe()+"\"" +
                "  }");
        AlipayOpenAppQrcodeCreateResponse response = null;
        try {
            response = alipayClient.execute(request,null,this.authToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return response.getQrCodeUrl();
    }

    /**
     * 关闭支付交易
     * @return
     */
    public boolean closeTradePay(String tradeNo,String outTradeNo,String authToken){

        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
                "\"trade_no\":\""+tradeNo+"\"," +
                "\"out_trade_no\":\""+outTradeNo+"\"," +
                //"\"operator_id\":\"YX01\"" + 操作人id
                "  }");
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request,null,this.authToken);

            return response.isSuccess();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 查询交易
     * @param tradeNo
     * @return
     */
    public AlipayTradeQueryResponse queryTradePay(String tradeNo,Integer customerId){

        AlipayClient alipayClient = alipayConfig.getCustomerClient(customerId);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":\""+tradeNo+"\"}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("查询交易-异常：{} {}",request.getBizContent(),e);
        }
        return response;
    }

    /**
     * 推送支付宝模板消息
     */
    public boolean sendTemplateMsg(TemplateMsgDTO templateModel,String authToken){
        AlipayClient alipayClient = alipayConfig.getDefaultClient();
        AlipayOpenAppMiniTemplatemessageSendRequest request =new AlipayOpenAppMiniTemplatemessageSendRequest();
        request.setBizContent(templateModel.build());
        AlipayOpenAppMiniTemplatemessageSendResponse response = null;
        try {
            response = alipayClient.execute(request,null,this.authToken);
        } catch (AlipayApiException e) {
           logger.error("推送支付宝模板-异常：{},{}",request.getBizContent(),e);
        }

        if(response.isSuccess()){
            return true;
        }else{
            logger.error("推送支付宝模板-失败 {}", response.getBody());
        }

        return false;
    }

    /**
     * 验证签名
     * @param params
     * @return
     */
    public boolean checkSign(Map<String,String> params) {
        try {
            String appId = params.get("app_id");
            String publicTBKey = alipayKeyService.getPublicTBKey(appId);
            boolean success = AlipaySignature.rsaCheckV1(params, publicTBKey, "utf-8", "RSA2");
            if (!success) {
                logger.error("支付宝验证签名-失败 AppId:{},TBKey:{} 参数：{}", appId, publicTBKey, params);
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验证签名-异常 {} {}", params, e);
        }
        return false;
    }

}
