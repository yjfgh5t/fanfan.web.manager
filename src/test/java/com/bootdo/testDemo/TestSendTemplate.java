package com.bootdo.testDemo;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AlipayKeyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JY
 * @date: 2018/9/20 15:02
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSendTemplate {

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    AlipayKeyService alipayKeyService;

    @Test
    public void SendMessage(){

        TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
        templateMsgDTO.setFormId("2018103022001474791006993587");
        templateMsgDTO.setToUserId("2088802637374794");
        //订单号、
        templateMsgDTO.setKeyword1("222002220022");
        //退款金额、
        templateMsgDTO.setKeyword2("1.0");
        //退款原因
        templateMsgDTO.setKeyword3("商家退款");

        //发送模板消息
        boolean send = alipayManager.sendTemplateMsg(templateMsgDTO.buildRefundOrder(77),null);

        System.out.println("发送："+send);
    }

    @Test
    public void queryTradePay(){
        Integer customerId=138;
        String tradeNo ="153743612215631878";

        AlipayTradeQueryResponse alipayTradeQueryResponse = alipayManager.queryTradePay(tradeNo, customerId);

        System.out.println(alipayTradeQueryResponse.getBody());
    }

    @Test
    public void tradeRefund(){

       boolean sucess = alipayManager.tradeRefund("15408920514530",0.01,132);

        System.out.println("退款："+sucess);

    }

}
