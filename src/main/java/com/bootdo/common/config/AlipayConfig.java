package com.bootdo.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.AlipayKeyService;
import com.bootdo.fanfan.service.DictionaryService;
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

    private  Map<Integer,AlipayClient> alipayClientMap = new HashMap<>();

    @Autowired
    AlipayKeyService alipayKeyService;

    @PostConstruct
    public void init(){
        AlipayKeyDO alipayKeyDO = alipayKeyService.getByCustomerId(bootdoConfig.getDefaultAlipayCustomerId());
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
        return getCustomerClient(bootdoConfig.getDefaultAlipayCustomerId());
    }

    /**
     * 获取第三方平台请求客户端
     * @return
     */
    public AlipayClient getDefaultPlatformClient(){
        return getCustomerClient(bootdoConfig.getDefaultAilpayPlatformCustomerId());
    }

    /**
     * 获取商户配置
     * @param customerId
     * @return
     */
    public  AlipayClient getCustomerClient(Integer customerId){

        if(!alipayClientMap.containsKey(customerId)) {
            AlipayKeyDO alipayKeyDO = alipayKeyService.getByCustomerId(customerId);

            if (alipayKeyDO != null) {
                DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(
                        bootdoConfig.getAlipayUrl(),
                        alipayKeyDO.getAppId(),
                        alipayKeyDO.getPrivateKey(),
                        "JSON",
                        "utf-8",
                        alipayKeyDO.getPublicTbKey(),
                        "RSA2");
                alipayClientMap.put(customerId, defaultAlipayClient);
            }else{
                throw new SecurityException("商户未配置支付宝密钥");
            }
        }

        return alipayClientMap.get(customerId);
    }

    /**
     * 删除商户配置缓存
     * @param customerId
     */
    public void removeCustomer(Integer customerId){
        if(alipayClientMap.containsKey(customerId)){
            alipayClientMap.remove(customerId);
        }
    }
}
