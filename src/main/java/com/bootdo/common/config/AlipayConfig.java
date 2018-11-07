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

    private String publicKey;
    @Getter
    private String appId;
    @Getter
    private String publicTBKey;

    private  Map<String,AlipayClient> aliPayClientMap = new HashMap<>();

    @Autowired
    AlipayKeyService alipayKeyService;

    @PostConstruct
    public void init(){
        AlipayKeyDO alipayKeyDO = alipayKeyService.getByAppId(bootdoConfig.getAliPayAppId());
        if(alipayKeyDO!=null){
            publicKey = alipayKeyDO.getPublicKey();
            appId = alipayKeyDO.getAppId();
            publicTBKey = alipayKeyDO.getPublicTbKey();
        }
    }

    /**
     * 获取小程序请求客户端
     * @return
     */
    public  AlipayClient getDefaultClient(){
        return getAppClient(bootdoConfig.getAliPayAppId());
    }

    /**
     * 获取第三方平台请求客户端
     * @return
     */
    public AlipayClient getDefaultPlatformClient(){
        return getAppClient(bootdoConfig.getAliPayPlatformAppId());
    }

    /**
     * 获取商户配置
     * @param appId
     * @return
     */
    public AlipayClient getAppClient(String appId){

        if(!aliPayClientMap.containsKey(appId)) {
            AlipayKeyDO alipayKeyDO = alipayKeyService.getByAppId(appId);
            if (alipayKeyDO != null) {
                DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(
                        bootdoConfig.getAliPayUrl(),
                        alipayKeyDO.getAppId(),
                        alipayKeyDO.getPrivateKey(),
                        "JSON",
                        "utf-8",
                        alipayKeyDO.getPublicTbKey(),
                        "RSA2");
                aliPayClientMap.put(appId, defaultAlipayClient);
            }else{
                throw new SecurityException("商户未配置支付宝密钥");
            }
        }
        return aliPayClientMap.get(appId);
    }

    /**
     * 删除商户配置缓存
     * @param appId
     */
    public void removeAppConfig(String appId){
        if(aliPayClientMap.containsKey(appId)){
            aliPayClientMap.remove(appId);
        }
    }
}
