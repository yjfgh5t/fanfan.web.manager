package com.bootdo.common.config;

import com.alibaba.druid.util.StringUtils;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.service.AlipayKeyService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayConfig {

    private String publicKey;
    private String appId;
    private String publicTBKey;
    public  final String alipayUrl="https://openapi.alipay.com/gateway.do";

    private  Map<Integer,AlipayClient> alipayClientMap = new HashMap<>();

    @Autowired
    AlipayKeyService alipayKeyService;

    @PostConstruct
    public void init(){
        AlipayKeyDO alipayKeyDO = alipayKeyService.getByCustomerId(132);
        if(alipayKeyDO!=null){
            publicKey = alipayKeyDO.getPublicKey();
            appId = alipayKeyDO.getAppId();
            publicTBKey = alipayKeyDO.getPublicTbKey();
        }
    }

    public String getAppId(){
        return appId;
    }

    /**
     * 获取默认对象
     * @return
     */
    public  AlipayClient getDefaultClient(){
        return getCustomerClient(132);
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
                        alipayUrl,
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
