package com.bootdo.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;

public class AlipayConfig {

    public static final String privateKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDyJ4ct0/1P7XeH" +
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

    public static final String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApADzsVMQ5zogiKyPNbtOpENv18EtLHrFsUx2hwmeOLfuHDyLBYJaxcieo2KQpFyzp/Az/DUEIMe3+0BU401Ajklqx791Xd3/yml3Qm7ohJPyCHGUt+hDgEFs+kbllg2VFc7DKXSKzCre3UW6+iZsulDTCJXegdJKiDSd9hZeH70X9554Qi5dyhXTpwwlILQG/D65/frLXaujdRZ5L7sMrNMgc3FV8cOLAucnfT3ViHHNbAALv/ROHK0IF2ddsKrLByvVqP2zSMCnDooyWBdU69dBptwiD1jDcv9DdxoGmqC2JOD8XwQXIGCy2wKNRbIoaheNaf6Myi0FTgQwn3kozQIDAQAB";
    public static final String appId="2018033102482725";
    public static final String alipayUrl="https://openapi.alipay.com/gateway.do";



    public static final String testPrivateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzZfvnLXSs7d+Q\n" +
            "/Qem/2IJkA1XsyhgRRWDp1rqlSR5L20bURCk8VUC3PCHsdmGQv1nSaBJNKD7GhK3\n" +
            "eCrjhtfObO2o4weE7PwuDiXtNh55SJtE0hykGEq/FHiNID7UnbnkGCMf2LyTweWM\n" +
            "gHNeNUgFKzyzD5zS9NhingaviWur+WCHu5rgMFto8Zecu1BIdI/UtseNlyHtJTlp\n" +
            "QSKh7wZHm6+ojuQcPvvOLPSB9mUOfruIrDlcjl3jpoQ/imIWOaJveKVmXmfQzM1j\n" +
            "Vc7UZ6ELcJD4FKc7pkC8t/0NiYQSkcUosqQnaZI8P0RqrLXyQlj6nGCGLPsOAHVw\n" +
            "puBKto3dAgMBAAECggEAexXc2Ic6hy+J9at1paGQ/SilzucwK6UalsKrQUnkxXTB\n" +
            "sj1TYyflgNpf+WoP6etqe4Rrge6nCMGh19mN65/nz0yzXu2tigkjGOJCKVe5XtNv\n" +
            "HK/gUMIN/r4n3AQkt7IkCZh5+O62q9YcDeNPxHJgIJPCyMRMf/zXvgnYYnWxt0Jz\n" +
            "NFngoh0iisZ+LrUF4/VZPPewzY4eZyGQ/pg6YM8Q/lIouryN4JroZtfspbyQIDXv\n" +
            "OZw+lEKd34ryFeuhk8OiVYN28WcYOY9aYEo3b5LkOU7tBmAONYD+PkNRfrL17/ne\n" +
            "RxUb971LUXdE15xEQc9dny8eC844wmAHbJGgVlpyoQKBgQDd/RYDjaHDKNN+ZKXw\n" +
            "pwPNy08sDTUlu8UL2JwTQrLaiAQnYZhyDh1sahck6omNOfAtDlNv8z0RO/zXXFMM\n" +
            "p3SgidBrJ/nKuCsdtTD/TPvckv7IWbTs6R5fSJ0YLo+65qUYdNrryWA0jNawUSzN\n" +
            "gEmqXNcyK+CJ9l9onHAHUrZxaQKBgQDO4miqwPrvUCwqvHxxJLRHklonygPOd+qD\n" +
            "dBdtdDCKYt6kPk0w7DkRWo1/q6+9h0ltAm53QQNywPKmEVbdvWYVCYU+w9UwknoG\n" +
            "52XqUTaJPeS/Nim6pMrYz0NkWCHy1HcCZ3+vI6rOKen5ds//I2lWyEih/AQH7AT9\n" +
            "4uQuXOj2VQKBgG3hsNvhZ4jq1IVj3J/ajA95656qivw6jbbnaZo/EbkKSsQTkxoh\n" +
            "0n6vpPUpAx1c6H3k8XggtsGpJ+aSVKmW2iv9C6V/DC5EK1NxNOeqe6EbQfJ6NQxv\n" +
            "+BDCKMCni43ATUhlz1eXPn/ElyB/jeXK8qvZeMvk3ChuQ5vMk8eMMbLBAoGAZxZK\n" +
            "MUuXvkekumbRW09+xWsXoVv4KZl3MalXQyLyTQKXDiA9NnN+vDby6vsRxs7yt27O\n" +
            "vH2YykV6o2Vgc308sJ7d1jiesmVKiMYk8m0lT52Xre+/O1GCAibE3etMf0N/SaNo\n" +
            "7ExJgBFClRuu6OMsSiHij1SUbZ4dnqTALpn9O0ECgYEAg6M5VRxUH5PO5DCeUnOX\n" +
            "nOI+aFx7QzuD+uKh/x6E0GXL61/+NMVWr/fc3/tEVz8KdQ+iJQ3kvxIWWqnCEpIY\n" +
            "ps55kHAtaL0xMy2EqIfU/Q7dtljwWITj9RqSOO7tXHe09mG3zZ6Cs6jjaGQxCmGn\n" +
            "o6LTQuGyiWLB9T/97V1RWL4=";
    public static final String testPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxnWcD0HmBGKAhO7gCMyz5SmKB0JeLQA7i2z2smFDMxm8YIR2ep3ou60+sUTltDCEI1ysBvd3nQf2wtwO0rK006mPCN23UtC6FoWsd5eZh7+4bmAMeF8ZGyVmLielv5nIH3uUiKJdODGGAhPozqRxqevcHBtRFfZJ4lq+zR6XH6gWmjVraKxWzCB+JF3uh5jIYTkd+1ZtOu8fD2K7oNtaDnKav+K33Kpbsbir8m/zPCeFwTyV2BeX+jGaNhB9vpXi0IWE7t1pM3+bA/kKV/yUGUYembrVPlYtu6O1VWnqiWbB0Afg53TeXFEeAVh5gKwPh8On7pFLfYC26INK1Ky1iwIDAQAB";
    public static final String testAppId="2016091000481099";
    public static final String testAlipayUrl="https://openapi.alipaydev.com/gateway.do";


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

    /**
     * 获取默认对象
     * @return
     */
    public static AlipayClient getTestDefaultClient(){
        return new DefaultAlipayClient(
                AlipayConfig.testAlipayUrl,
                AlipayConfig.testAppId,
                AlipayConfig.testPrivateKey,
                "JSON",
                "UTF-8",
                AlipayConfig.testPublicKey,
                "RSA2");
    }
}
