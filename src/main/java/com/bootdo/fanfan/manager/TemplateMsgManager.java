package com.bootdo.fanfan.manager;

import com.bootdo.common.msgQueue.AbstractMsgQueue;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.DTO.FormUserDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgDTO;
import com.bootdo.fanfan.domain.DTO.TemplateMsgMQDTO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderPayType;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.domain.enumDO.OrderTypeEnum;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.FormIdService;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.ShopService;
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

    @Autowired
    ShopService shopService;

    @Override
    protected void runCallback(TemplateMsgMQDTO item) {

        switch (item.getTemplateType()) {
            case 1:
                //付款消息
                sendPayTempMsg(item);
                break;
            case 2:
                //退款消息
                sendRefundTempMsg(item);
                break;
            case 3:
                //取消订单消息
                sendCancelTempMsg(item);
                break;
            default:
        }
    }

    /**
     * 推送退款模板消息
     */
    private void sendRefundTempMsg(TemplateMsgMQDTO item) {

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
                templateMsgDTO.setKeyword1("#"+orderDO.getOrderDateNum());
                //退款金额、
                templateMsgDTO.setKeyword2(orderDO.getOrderPay().toString());
                //退款原因
                String remark = orderDO.getOrderCustomerRemark();
                if(StringUtils.isEmpty(remark)){
                    remark ="商家取消订单";
                }
                templateMsgDTO.setKeyword3(remark);

                //发送模板消息
               boolean send = alipayManager.sendTemplateMsg(templateMsgDTO.buildRefundOrder(item.getOrderId()),null);
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
                templateMsgDTO.setKeyword1("#"+orderDO.getOrderDateNum());
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

    /**
     * 推送取消订单模板消息
     * @param item
     */
    private void sendCancelTempMsg(TemplateMsgMQDTO item) {
        StringBuilder builder = new StringBuilder("开始推送下订单模板---->\t");

        OrderDO orderDO = orderService.get(item.getOrderId());
        //获取FormId
        FormUserDTO recordDO = formIdService.getCanUseFormId(PlatformEnum.AlipayMiniprogram,orderDO.getUserId());
        builder.append("1.推送支付宝模板消息 userId:" + orderDO.getUserId());
        if (recordDO != null) {
            TemplateMsgDTO templateMsgDTO = new TemplateMsgDTO();
            templateMsgDTO.setFormId(recordDO.getFormId());
            templateMsgDTO.setToUserId(recordDO.getTpId());
            //订单号码
            templateMsgDTO.setKeyword1("#"+orderDO.getOrderDateNum());
            //订单状态、
            templateMsgDTO.setKeyword2(OrderStateEnum.get(orderDO.getOrderState()).getText());
            //商户名称
            String shopName = shopService.getNameByCustomerId(orderDO.getCustomerId());
            templateMsgDTO.setKeyword3(shopName);
            //发送模板消息
            boolean send = alipayManager.sendTemplateMsg(templateMsgDTO.buildCancelOrder(item.getOrderId()), null);
            builder.append("2.执行发送结果:" + send);
        }
        logger.info(builder.toString());
    }
}
