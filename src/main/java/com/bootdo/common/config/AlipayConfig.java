package com.bootdo.common.config;

import com.alibaba.druid.util.StringUtils;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bootdo.common.exception.BDException;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.AlipayKeyService;
import com.bootdo.fanfan.service.DictionaryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayConfig {
    @Autowired
    AlipayKeyService alipayKeyService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    BootdoConfig bootdoConfig;


    private String publicKey;
    private String appId;
    private String publicTBKey;
    private String privateKey;
    public  final String alipayUrl="https://openapi.alipay.com/gateway.do";
    private DefaultAlipayClient defaultAlipayClient;

    @PostConstruct
    public void init() {

        Map<Integer, String> results = dictionaryService.queryByKeys(bootdoConfig.getDefaultCustomerId(),
                DictionaryEnum.aliPayPrivateKey.getVal(),
                DictionaryEnum.aliPayKey.getVal(),
                DictionaryEnum.aliPayPublicKey.getVal(),
                DictionaryEnum.appId.getVal());

        publicKey = results.get(DictionaryEnum.aliPayPublicKey.getVal());
        appId = results.get(DictionaryEnum.appId.getVal());
        publicTBKey = results.get(DictionaryEnum.aliPayKey.getVal());
        privateKey = results.get(DictionaryEnum.aliPayPrivateKey.getVal());

        defaultAlipayClient = new DefaultAlipayClient(
                alipayUrl,
                appId,
                privateKey,
                "JSON",
                "utf-8",
                publicTBKey,
                "RSA2");
    }

    public String getAppId(){
        return appId;
    }

    public String getPublicTBKey(){
        return publicTBKey;
    }

    /**
     * 获取默认对象
     * @return
     */
    public  AlipayClient getDefaultClient() {
        return new DefaultAlipayClient(
                alipayUrl,
                appId,
                privateKey,
                "JSON",
                "utf-8",
                publicTBKey,
                "RSA2");
    }
}
