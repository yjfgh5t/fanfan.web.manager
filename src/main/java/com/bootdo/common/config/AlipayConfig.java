package com.bootdo.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayConfig {

    public static final String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRtSb1qyol6hGboRotYwqcDEY2ugXoNQHQz5YEsi4ehrBRTR6dVeJrSDgNCf0jhcntQ2ymzstP9Mvu9a8zisSL1l9/FSrokVcSMLMbJNIvBhAGuZI9zHivrle9vS+tESYphVQHUvLeE9NrniZIngu/T+pHBy5YBcegj6TZ00OBfcll9SUuZD1H/sVy0+BYeAoQTuw2n/L2w6rKq+yqSay+XCEUh+OwUfoW6guYCzXNWvHBWN7FTRYCpCUlJBAlgk7T3rP/9WTO5aIn6KNDGgN7IZmrcI0O/xLnDtN+mQ0P0u30++JuSC8qKEkgHTdyMdQwp2j3cpFCmJa8n0ZYDKIPAgMBAAECggEAQwH3H8WOhY8md9bXzgWdLHRsmeBktQwjgJ7mk9RJZ1pznYAE00Ba9xFoTX7JfluZqFZ4kiHJ2ERwwL0VzHNFvjVhB3V5nSixzTi+NX5ydpOoLR+MRIVS/yADU8u8MDARx8h8Az4/WKfvc69wVbbxGhMrX2zLAEnwK0PEATSp99NqCkWKLWEFT8tbUJ7gaaVIqlBF6229EyPkSMsQ2qrIoULQtpgtg8W76jHWSbvszawFsDlw6dOYDajxSZ/jd8j68coOvZ9IqToFGar++vmG2N0RMdKONd7u5B2XDuZd+6s2q3AZVZLjeBQ6BUcKLss67Jk9QgHbJvmvo3FErApsWQKBgQDO7f9taYoYeEkmZljGWz9vbznf2m+54Z5YTGbqQ7U57BUU0iwFbgc3HfhiOQBDxsLs2FrRJuejbYTUsVzIRWq8nUHZNAzjT5r2BiB+T4aIxYdiC0mGXXkojIvME36cjMktAW34l3TMsDat4qZCLpx+b/O8+YlqokU4BbctnG9bRQKBgQC0QpH8ejjsFxQkLv2CYC20H4HR92+Kkymot+GZTpZkIF4Mv5Yk+vDFRDPm7Bm2PXU5i5MUMNha6oeAeO7LQSYh0q5ZBzrempgTQo8rcxmxtovEvtfjMYjdaIgDcpDdEImgr4uHT9IPKu1IOWMWNH6ZVz091NMMVr5jjhGA9+UzQwKBgAC4Sz8yMszK/lYn3P8zqhDVLjpdJC5dXpOoEGOYQLUm4Rvlb0nFjDjg3dBZqmqR8nOofQhlPkEPTHsA8vWvaCOn2GbNC45u5HceplZKKImpRkNNDsBs6tL9Si83kiUxSDHj42IOBNYBdu877qAkUV8PPKXXqGa6kcsUGgMKnEiRAoGAEdIO+qXeoMB7VkRq/YuimQ8QV4Cdv1ZjJPHg1LYnOgjVI2+BWXCRgv8GHosy04hp4WGCDdnyWbKb6MCL4v4y1d8tK1ICqOUhqa3H16TOYnpIgQ/Y6fYIsNLXt3eJCMHMyVzxb1Qd0TAOOzipKhXQejpcrJMwtiSRN+hgpJ5dQ18CgYBHy9BS0ZyiABeZZy7wpNReeD47au6xVW4ARJz4i0v6hObmHhXW/S5VpEe1CRLEx7HWvoNG7Y9Y0TlRE5MUOWT43zg0nJKqzXuu8k1uS8gzwCpDyN6dxoolATdlTqql+DYrYlWZgDGYFUJpCkW1k6DyYgnsjk1tnqpNeD1w7cCnbw==";

    public static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApADzsVMQ5zogiKyPNbtOpENv18EtLHrFsUx2hwmeOLfuHDyLBYJaxcieo2KQpFyzp/Az/DUEIMe3+0BU401Ajklqx791Xd3/yml3Qm7ohJPyCHGUt+hDgEFs+kbllg2VFc7DKXSKzCre3UW6+iZsulDTCJXegdJKiDSd9hZeH70X9554Qi5dyhXTpwwlILQG/D65/frLXaujdRZ5L7sMrNMgc3FV8cOLAucnfT3ViHHNbAALv/ROHK0IF2ddsKrLByvVqP2zSMCnDooyWBdU69dBptwiD1jDcv9DdxoGmqC2JOD8XwQXIGCy2wKNRbIoaheNaf6Myi0FTgQwn3kozQIDAQAB";
    public static final String appId="2018072060650548";
    public static final String alipayUrl="https://openapi.alipay.com/gateway.do";


    public static final String payPrivateKey ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDyJ4ct0/1P7XeH" +
            "C2Y5VRuXUJpS6qLpm2OzJuEHNCkPZuxuX4hBdZ+t+eq0shcA7LkWvlz9XrBSGELc" +
            "2wFZazNsOAcuQESfkfsW1F5pTvGNoI1seU2P+QSDbLSbXkjehcQxC6Rx6XEIEKw2" +
            "GgnrQAMVowwZ3W0EutYzXIH6uNNaBthpKF0NzgU8phK/98QdWmb5yZr5SyUQtwcZ" +
            "25h4WjlNuYs9rt/VXouiKvleq3eUy5omKx8c+5r8IRFV4z0wldqxIsh6NlbK8P8A" +
            "qmAYqje09ScBFpSjta0NB+Nf5qCYQBeIlE3+24mW/ORdQNrbA74IiornbESR7Y8U" +
            "lq5VxdOfAgMBAAECggEBAOxFpz8O7cWz9QE+HSLpqL8udTVcHVMEO/ZMhBosSbfu" +
            "e9iBvAfqxb/R7rEaZxmO4FWRrlfFsRKY+3xU7+8YixcXX+I/8PUlUbIHyorD7Sp8" +
            "MiFaBlTo6v907JAAIeeE61XTf0NqO9BbvDwZkf4I+jl9WkxOYZXQ2zlIFMbW2dmm" +
            "mEzIJ2r1GpdNaKupPD85pv9iFQkP5bkcWsjCWAMMD6kFi42iRCDtbYWr062FGSy+" +
            "C1zKI7J6osT0/wECwKfxmp+nm0oEO6E4Jt325KxrBHweP8pRMmnVye2TN/rKDstr" +
            "oSWEb4I4BdyCHPolLQQ46Jh/AdGOpYZzzXfHWAVBASkCgYEA/Fy7Z+ov1ly76FaU" +
            "kKs9fH+h2HIZGt4r15m9VAsHpDXyHFH2BChHkKG44h9jIazMoS1sog7EQTYh5UXa" +
            "DX8/2sAt+OrqsoI2xo0/bfV3j2A23RLtNPOR3nSPB23aM17XCKbj+95RuMBRRy7D" +
            "hO6e14LjLT0yqtdYINE7a50J93UCgYEA9aUghOLfRt+okssII5owE1QAkIQ8uMyo" +
            "6l9itgXp1mmmCMa7JSBg7R0IaBZYImYLHlo50g7gHV39AqrDBv+7+R9RuAZoYkP6" +
            "749Lb6wQkpiG0Ua/8sEaBQFA6bF7HIdseYx2EAki18xkxWtNHhgGILTbHfnX88cg" +
            "C9DxYu5P0EMCgYEA8BcRPIWyyVAkfaCRaylfL+kFgzTZ6ZPhRhc/5osv0+x20Lax" +
            "RbdEjrctfAO8fjc130mmBm/iCuCDUusnzpt3lpgrpJtqejYkArYy2FwmSUOSLrVo" +
            "822fI3Lk+H5sj171WYfdcqxKN2jQatZ0sS2L9SDTHSER6TNC5sXosDMRXlkCgYEA" +
            "itBsxwuaF2gO0B1yFQRuBI4k34YK9neZQRD0LF69HRCI5k6/tSROAdC1TnTKC25U" +
            "/CsKn70OQyo470fmfE8KP6bGPFNKq7U02inE/G9k0iLllOABWsJq3McvkqBcLOwO" +
            "jcWldjxjd0Wm8LVJWCdFVzg81vy4X6d4YZUKw9hePBcCgYANnxf+nwBfschEERhR" +
            "9YRNZrZxmHGgDFvOBOdS+t0kEXJ/zJ83TWH69AvPhW5BrMf/i0mczUSqGfAebOWz" +
            "NCdUlo+QaHFojBriwyEb1rNe0XvB4P2rpNevfVWQgxnN0kLyiUzqppDO89li2Lc/" +
            "AM4Q+qsuXyzJFVdMNT8qjrQk0A==";
    public static final String payAppId="2018033102482725";

    /**
     * 获取默认对象
     * @return
     */
    public static AlipayClient getDefaultClient(){

        return new DefaultAlipayClient(
                AlipayConfig.alipayUrl,
                AlipayConfig.appId,
                AlipayConfig.privateKey,
                "JSON",
                "utf-8",
                AlipayConfig.publicKey,
                "RSA2");
    }

    public static AlipayClient getPayClient() {
        return new DefaultAlipayClient(
                AlipayConfig.alipayUrl,
                AlipayConfig.payAppId,
                AlipayConfig.payPrivateKey,
                "JSON",
                "utf-8",
                AlipayConfig.publicKey,
                "RSA2");
    }
}
