package com.bootdo.fanfan.manager;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.bootdo.common.config.AlipayConfig;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import org.springframework.stereotype.Component;

@Component
public class AlipayManager {

    private static final String alipayToken="alipay_token",accessToken="alipay_access_token",refshToken="alipay_refsh_token";

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
            response = alipayClient.execute(request);
            if(response.isSuccess()){
                return response.getAccessToken();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
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

            response = alipayClient.execute(request,_token);

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
        request.setBizModel(model);
        request.setNotifyUrl("http://m.wxcard.com.cn/");
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
     * 关闭支付交易
     * @return
     */
    private boolean closeTradePay(String tradeNo,String outTradeNo){

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
}
