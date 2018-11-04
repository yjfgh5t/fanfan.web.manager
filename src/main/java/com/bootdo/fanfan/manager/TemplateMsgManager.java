package com.bootdo.fanfan.manager;

import com.bootdo.common.msgQueue.AbstractMsgQueue;
import com.bootdo.common.task.RefshOrderJob;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.DTO.FormUserDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgMQDTO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderPayType;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.FormIdService;
import com.bootdo.fanfan.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: JY
 * @date: 2018/8/10 15:44
 */
@Component
public class TemplateMsgManager extends AbstractMsgQueue<TemplateMsgMQDTO> {

    private static final Logger logger = LoggerFactory.getLogger(TemplateMsgManager.class);

    @Autowired
    OrderService orderService;

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    FormIdService formIdService;

    @Autowired
    AlipayRecordService alipayRecordService;

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

        StringBuilder builder = new StringBuilder("开始推送取消订单模板---->\t");

        OrderDO orderDO = orderService.get(item.getOrderId());

        //发送支付宝模板消息
        if (orderDO != null && PlatformEnum.AlipayMiniprogram.getVal().equals(orderDO.getOrderPayType())) {

            //获取FormId
            AlipayRecordDO recordDO = alipayRecordService.getByOutTradeNo(orderDO.getOrderNum());

            builder.append("1.推送支付宝模板消息 userId:"+recordDO.getBuyerId());
            if (recordDO!=null) {
                TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
                templateMsgDTO.setFormId(recordDO.getTradeNo());
                templateMsgDTO.setToUserId(recordDO.getBuyerId());
                //订单号、
                templateMsgDTO.setKeyword1(orderDO.getOrderDateNum());
                //退款金额、
                templateMsgDTO.setKeyword2(orderDO.getOrderPay().toString());
                //退款原因
                String remark = orderDO.getOrderCustomerRemark();
                if(StringUtils.isEmpty(remark)){
                    remark ="商家取消订单";
                }
                templateMsgDTO.setKeyword3(remark);

                //发送模板消息
               boolean send = alipayManager.sendTemplateMsg(templateMsgDTO.buildCancelPayOrder(item.getOrderId()),null);
                builder.append("2.执行发送结果:"+send);
            }
        }

        logger.info(builder.toString());
    }

    /**
     * 推送支付订单模板消息
     */
    private void sendPayTempMsg(TemplateMsgMQDTO item) {

        StringBuilder builder = new StringBuilder("开始推送下订单模板---->\t");

        OrderDO orderDO = orderService.get(item.getOrderId());
        if (orderDO != null && OrderPayType.Alipay.getId().equals(orderDO.getOrderPayType())) {
            //获取FormId
            AlipayRecordDO recordDO = alipayRecordService.getByOutTradeNo(orderDO.getOrderNum());
            builder.append("1.推送支付宝模板消息 userId:"+recordDO.getBuyerId());
            if (recordDO!=null) {
                TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
                templateMsgDTO.setFormId(recordDO.getTradeNo());
                templateMsgDTO.setToUserId(recordDO.getBuyerId());
                //订单号码
                templateMsgDTO.setKeyword1(orderDO.getOrderDateNum());
                //订单金额、
                templateMsgDTO.setKeyword2(orderDO.getOrderPay().toString());
                //下单时间
                templateMsgDTO.setKeyword3(DateUtils.format(orderDO.getOrderTime()));
                //发送模板消息
                boolean send = alipayManager.sendTemplateMsg(templateMsgDTO.buildPaySuccess(item.getOrderId()),null);
                builder.append("2.执行发送结果:"+send);
            }
        }
        logger.info(builder.toString());
    }
}
