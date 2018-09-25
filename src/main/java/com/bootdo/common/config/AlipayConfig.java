package com.bootdo.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.service.AlipayKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class AlipayConfig {

    public  final String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRtSb1qyol6hGboRotYwqcDEY2ugXoNQHQz5YEsi4ehrBRTR6dVeJrSDgNCf0jhcntQ2ymzstP9Mvu9a8zisSL1l9/FSrokVcSMLMbJNIvBhAGuZI9zHivrle9vS+tESYphVQHUvLeE9NrniZIngu/T+pHBy5YBcegj6TZ00OBfcll9SUuZD1H/sVy0+BYeAoQTuw2n/L2w6rKq+yqSay+XCEUh+OwUfoW6guYCzXNWvHBWN7FTRYCpCUlJBAlgk7T3rP/9WTO5aIn6KNDGgN7IZmrcI0O/xLnDtN+mQ0P0u30++JuSC8qKEkgHTdyMdQwp2j3cpFCmJa8n0ZYDKIPAgMBAAECggEAQwH3H8WOhY8md9bXzgWdLHRsmeBktQwjgJ7mk9RJZ1pznYAE00Ba9xFoTX7JfluZqFZ4kiHJ2ERwwL0VzHNFvjVhB3V5nSixzTi+NX5ydpOoLR+MRIVS/yADU8u8MDARx8h8Az4/WKfvc69wVbbxGhMrX2zLAEnwK0PEATSp99NqCkWKLWEFT8tbUJ7gaaVIqlBF6229EyPkSMsQ2qrIoULQtpgtg8W76jHWSbvszawFsDlw6dOYDajxSZ/jd8j68coOvZ9IqToFGar++vmG2N0RMdKONd7u5B2XDuZd+6s2q3AZVZLjeBQ6BUcKLss67Jk9QgHbJvmvo3FErApsWQKBgQDO7f9taYoYeEkmZljGWz9vbznf2m+54Z5YTGbqQ7U57BUU0iwFbgc3HfhiOQBDxsLs2FrRJuejbYTUsVzIRWq8nUHZNAzjT5r2BiB+T4aIxYdiC0mGXXkojIvME36cjMktAW34l3TMsDat4qZCLpx+b/O8+YlqokU4BbctnG9bRQKBgQC0QpH8ejjsFxQkLv2CYC20H4HR92+Kkymot+GZTpZkIF4Mv5Yk+vDFRDPm7Bm2PXU5i5MUMNha6oeAeO7LQSYh0q5ZBzrempgTQo8rcxmxtovEvtfjMYjdaIgDcpDdEImgr4uHT9IPKu1IOWMWNH6ZVz091NMMVr5jjhGA9+UzQwKBgAC4Sz8yMszK/lYn3P8zqhDVLjpdJC5dXpOoEGOYQLUm4Rvlb0nFjDjg3dBZqmqR8nOofQhlPkEPTHsA8vWvaCOn2GbNC45u5HceplZKKImpRkNNDsBs6tL9Si83kiUxSDHj42IOBNYBdu877qAkUV8PPKXXqGa6kcsUGgMKnEiRAoGAEdIO+qXeoMB7VkRq/YuimQ8QV4Cdv1ZjJPHg1LYnOgjVI2+BWXCRgv8GHosy04hp4WGCDdnyWbKb6MCL4v4y1d8tK1ICqOUhqa3H16TOYnpIgQ/Y6fYIsNLXt3eJCMHMyVzxb1Qd0TAOOzipKhXQejpcrJMwtiSRN+hgpJ5dQ18CgYBHy9BS0ZyiABeZZy7wpNReeD47au6xVW4ARJz4i0v6hObmHhXW/S5VpEe1CRLEx7HWvoNG7Y9Y0TlRE5MUOWT43zg0nJKqzXuu8k1uS8gzwCpDyN6dxoolATdlTqql+DYrYlWZgDGYFUJpCkW1k6DyYgnsjk1tnqpNeD1w7cCnbw==";

    public  final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApADzsVMQ5zogiKyPNbtOpENv18EtLHrFsUx2hwmeOLfuHDyLBYJaxcieo2KQpFyzp/Az/DUEIMe3+0BU401Ajklqx791Xd3/yml3Qm7ohJPyCHGUt+hDgEFs+kbllg2VFc7DKXSKzCre3UW6+iZsulDTCJXegdJKiDSd9hZeH70X9554Qi5dyhXTpwwlILQG/D65/frLXaujdRZ5L7sMrNMgc3FV8cOLAucnfT3ViHHNbAALv/ROHK0IF2ddsKrLByvVqP2zSMCnDooyWBdU69dBptwiD1jDcv9DdxoGmqC2JOD8XwQXIGCy2wKNRbIoaheNaf6Myi0FTgQwn3kozQIDAQAB";
    public  final String appId="2018033102482725";
    public  final String alipayUrl="https://openapi.alipay.com/gateway.do";

    private  Map<Integer,AlipayClient> alipayClientMap = new HashMap<>();

    @Autowired
    AlipayKeyService alipayKeyService;

    @PostConstruct
    private void init() {

        DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(
                alipayUrl,
                appId,
                privateKey,
                "JSON",
                "utf-8",
                publicKey,
                "RSA2");
        alipayClientMap.put(0,defaultAlipayClient);
    }

    /**
     * 获取默认对象
     * @return
     */
    public  AlipayClient getDefaultClient(){
        return alipayClientMap.get(0);
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
}
