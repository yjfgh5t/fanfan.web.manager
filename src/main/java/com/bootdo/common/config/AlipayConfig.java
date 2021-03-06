package com.bootdo.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.service.AlipayKeyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayConfig {

    @Autowired
    BootdoConfig bootdoConfig;

    /**
     * 小程序Id
     */
    @Getter
    private String appId;

    /**
     * 平台TbKey
     */
    @Getter
    private String publicPlatformTBKey;

    /**
     * 获取小程序请求客户端
     */
    @Getter
    private AlipayClient defaultClient;

    /**
     * 获取平台小程序请求客户端
     */
    @Getter
    private AlipayClient defaultPlatformClient;


    @Autowired
    AlipayKeyService alipayKeyService;

    @PostConstruct
    public void init(){
        //初始化小程序Client
        AlipayKeyDO alipayKeyDO = alipayKeyService.getByAppId(bootdoConfig.getAliPayAppId());
        defaultClient = getAppClient(alipayKeyDO);
        appId = alipayKeyDO.getAppId();

        //初始化平台小程序Client
        alipayKeyDO = alipayKeyService.getByAppId(bootdoConfig.getAliPayPlatformAppId());
        defaultPlatformClient = getAppClient(alipayKeyDO);
        publicPlatformTBKey = alipayKeyDO.getPublicTbKey();
    }


    private AlipayClient getAppClient(AlipayKeyDO alipayKeyDO) {
        if (alipayKeyDO != null) {
            DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(
                    bootdoConfig.getAliPayUrl(),
                    alipayKeyDO.getAppId(),
                    alipayKeyDO.getPrivateKey(),
                    "JSON",
                    "utf-8",
                    alipayKeyDO.getPublicTbKey(),
                    "RSA2");
            return defaultAlipayClient;
        } else {
            throw new SecurityException("商户未配置支付宝密钥");
        }
    }

}
