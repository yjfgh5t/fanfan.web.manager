package com.bootdo.fanfan.manager;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.task.RefshOrderJob;
import com.bootdo.fanfan.domain.DTO.QRCodeDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AlipayManager {

    private static final String alipayToken="alipay_token",accessToken="alipay_access_token",refshToken="alipay_refsh_token";

    private static final Logger logger = LoggerFactory.getLogger(RefshOrderJob.class);

    @Autowired
    static BootdoConfig bootdoConfig;

    /**
     * 获取用信息
     * @param code
     */
    public String getToken(String code){

        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        //请求对象
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        //当有code时 更具code获取token
        request.setGrantType("authorization_code");
        request.setCode(code);
        //接收对象
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request,null,"201809BB4de9a2bfd3134b02a45cfe07470ebX33");
            if(response.isSuccess()){
                return response.getAccessToken();
            }else{
                logger.error("调用接口失败："+ response.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "201809BB4de9a2bfd3134b02a45cfe07470ebX33";
    }

    /**
     * 获取用户信息
     * @param code
     * @return
     */
    public AlipayUserInfoShareResponse getUserInfo(String  code){
        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;

        try {
            String _token  =  this.getToken(code);
            if(_token==null){
                System.out.println("获取token失败");
            }

            response = alipayClient.execute(request,_token,"201809BB4de9a2bfd3134b02a45cfe07470ebX33");

            if(response.isSuccess()){
                System.out.println(response.getUserName());
                return response;
            } else {
                System.out.println("调用失败");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建支付预付单
     * @param alipayDO
     * @return
     */
    public String  createTradePay(OrderAlipayDO alipayDO){
        //实例化客户端
        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
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
            return response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 退款
     */
    public boolean tradeRefund(String orderNum,String refundAmount){
        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+orderNum+"\"," +
                "\"refund_amount\":"+refundAmount+"" +
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            //执行退款
            response = alipayClient.execute(request);
            return  response.isSuccess() && response.getCode()=="10000";

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 创建小程序二维码
     * @return
     */
    public String createQRCode(QRCodeDTO codeDTO){

        //实例化客户端
        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayOpenAppQrcodeCreateRequest request = new AlipayOpenAppQrcodeCreateRequest();
        request.setBizContent("{" +
                "\"url_param\":\""+codeDTO.getUrlParam()+"\"," +
                "\"query_param\":\""+codeDTO.getQueryParamToString()+"\"," +
                "\"describe\":\""+codeDTO.getDescribe()+"\"" +
                "  }");
        AlipayOpenAppQrcodeCreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return response.getQrCodeUrl();
    }

    /**
     * 关闭支付交易
     * @return
     */
    public boolean closeTradePay(String tradeNo,String outTradeNo){

        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
                "\"trade_no\":\""+tradeNo+"\"," +
                "\"out_trade_no\":\""+outTradeNo+"\"," +
                //"\"operator_id\":\"YX01\"" + 操作人id
                "  }");
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);

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
    public AlipayTradeQueryResponse queryTradePay(String tradeNo){

        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"trade_no\":\""+tradeNo+"\"}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            logger.error("查询支付宝交易异常- ->{}",e);
        }
        return response;
    }

    /**
     * 推送支付宝模板消息
     */
    public boolean sendTemplateMsg(TemplateMsgDTO templateModel){
        AlipayClient alipayClient = AlipayConfig.getDefaultClient();
        AlipayOpenAppMiniTemplatemessageSendRequest request =new AlipayOpenAppMiniTemplatemessageSendRequest();
        request.setBizContent(templateModel.build());
        AlipayOpenAppMiniTemplatemessageSendResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            logger.info("模板发送成功");
            return true;
        }else{
            logger.error("模板发送失败--{}", JSON.toJSONString(response));
        }

        return false;
    }

    /**
     * 验证签名
     * @param params
     * @return
     */
    public boolean checkSign(Map<String,String> params){

        try {
            return AlipaySignature.rsaCheckV1(params,AlipayConfig.publicKey,"utf-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return false;
    }

}
