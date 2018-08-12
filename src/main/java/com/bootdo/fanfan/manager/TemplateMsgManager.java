package com.bootdo.fanfan.manager;

import com.bootdo.common.msgQueue.AbstractMsgQueue;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgMQDTO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.FormIdService;
import com.bootdo.fanfan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: JY
 * @date: 2018/8/10 15:44
 */
@Component
public class TemplateMsgManager extends AbstractMsgQueue<TemplateMsgMQDTO> {

    @Autowired
    OrderService orderService;

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    FormIdService formIdService;

    @Override
    protected void runCallback(TemplateMsgMQDTO item) {

        switch (item.getTemplateType()) {
            case 1:
                sendPayTempMsg(item);
                break;
            case 2:
                sendCancleTempMsg(item);
                break;
            default:
        }
    }

    /**
     * 推送退单模板消息
     */
    private void sendCancleTempMsg(TemplateMsgMQDTO item) {

        OrderDO orderDO = orderService.get(item.getOrderId());

        //发送支付宝模板消息
        if (orderDO != null && PlatformEnum.AlipayMiniprogram.getVal().equals(orderDO.getOrderPayType())) {

            //获取FormId
            String formId = formIdService.getCanUseFormId(PlatformEnum.AlipayMiniprogram, orderDO.getUserId());
            if (StringUtils.isNotEmpty(formId)) {
                TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
                templateMsgDTO.setFormId("");
                //订单号、
                templateMsgDTO.setKeyword1(orderDO.getOrderDateNum());
                //退款金额、
                templateMsgDTO.setKeyword2(orderDO.getOrderPay().toString());
                //退款原因
                templateMsgDTO.setKeyword3(orderDO.getOrderCustomerRemark());

                //发送模板消息
                alipayManager.sendTemplateMsg(templateMsgDTO.buildCancelPayOrder(item.getOrderId()));
            }
        }
    }

    /**
     * 推送支付订单模板消息
     */
    private void sendPayTempMsg(TemplateMsgMQDTO item) {

        OrderDO orderDO = orderService.get(item.getOrderId());
        if (orderDO != null && PlatformEnum.AlipayMiniprogram.getVal().equals(orderDO.getOrderPayType())) {
            //获取FormId
            String formId = formIdService.getCanUseFormId(PlatformEnum.AlipayMiniprogram, orderDO.getUserId());
            if (StringUtils.isNotEmpty(formId)) {
                TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
                templateMsgDTO.setFormId("");
                //订单号码
                templateMsgDTO.setKeyword1(orderDO.getOrderDateNum());
                //订单金额、
                templateMsgDTO.setKeyword2(orderDO.getOrderPay().toString());
                //下单时间
                templateMsgDTO.setKeyword3(DateUtils.format(orderDO.getOrderTime()));
                //发送模板消息
                alipayManager.sendTemplateMsg(templateMsgDTO.buildPaySuccess(item.getOrderId()));
            }
        }
    }
}
