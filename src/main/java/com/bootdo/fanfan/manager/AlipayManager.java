package com.bootdo.fanfan.manager;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.bootdo.common.config.AlipayConfig;
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

}
