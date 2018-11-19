package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.OrderStateService;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import com.bootdo.fanfan.vo.APIPrintOrderDetailVO;
import com.bootdo.fanfan.vo.APIPrintOrderVO;
import com.bootdo.fanfan.vo.model.XGPushModel;
import com.tencent.xinge.XingeApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author: JY
 * @date: 2018/11/7 13:01
 */
@RestController
@RequestMapping("api/debug")
public class DebugRestController {

    @Autowired
    XGPushManager xgPushManager;

    @Autowired
    private EMapper mapper;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderStateService orderStateService;

    @Autowired
    AlipayRecordService alipayRecordService;

    @GetMapping("pushNotify")
    public R pushNotify(Integer customerId,String message){
        XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.authorizeNotify,customerId.longValue());
        pushModel.setMsgTitle(message);
        pushModel.setMsgContent("支付宝授权成功");
        pushModel.setNotification(true);
        pushModel.addParams("data", "success");
        pushModel.addParams("time", Calendar.getInstance().getTime());
        //推送消息
        xgPushManager.put(pushModel);

        return R.ok();
    }


    /**
     * 测试推送订单
     *
     * @param id
     * @return
     */
    @GetMapping("/pushOrder/{id}")
    public R debugPush(@PathVariable("id") Integer id) {
        APIOrderRequVO apiOrderRequVO = orderService.queryOrder(id);
        XGPushModel pushModel = new XGPushModel(XGPushModel.MsgType.payOrder, apiOrderRequVO.getCustomerId().longValue());
        pushModel.setMsgTitle("您有新的订单");
        pushModel.setMsgContent("订单总额："+new Random().nextInt());
        pushModel.setNotification(false);
        //数据转换
        APIPrintOrderVO data = mapper.map(apiOrderRequVO, APIPrintOrderVO.class);
        if(apiOrderRequVO.getDetailList()!=null){
            List<APIPrintOrderDetailVO> detailVOS = mapper.mapArray(apiOrderRequVO.getDetailList(), APIPrintOrderDetailVO.class);
            data.setDetails(detailVOS);
        }
        pushModel.addParams("data", JSONObject.toJSONString(data));
        pushModel.addParams("time",Calendar.getInstance().getTime());

        //推送消息
        xgPushManager.put(pushModel);
        return R.ok();
    }

    @GetMapping("/pay/{id}")
    public R debugPay(@PathVariable("id") Integer id) {

        boolean hasPay = orderStateService.orderHasPay(id);
        if (hasPay) {
            return R.ok().put("data", "订单已经支付");
        }
        OrderDO orderInfo = orderService.get(id);

        if (orderInfo == null) {
            return R.error(1, "订单不存在");
        }

        //查询支付宝订单
        AlipayTradeQueryResponse tradeQueryResponse = new AlipayTradeQueryResponse();
        tradeQueryResponse.setOutTradeNo(orderInfo.getOrderNum());
        tradeQueryResponse.setTotalAmount(orderInfo.getOrderTotal().doubleValue() + "");
        tradeQueryResponse.setTradeNo("Debug-" + Calendar.getInstance().getTime().getTime());
        tradeQueryResponse.setPayAmount(orderInfo.getOrderTotal().doubleValue() + "");
        tradeQueryResponse.setBuyerUserId(orderInfo.getUserId() + "");

        //判断是否支付成功
        if (tradeQueryResponse != null && tradeQueryResponse.isSuccess()) {
            AlipayRecordDO recordDO = mapper.map(tradeQueryResponse, AlipayRecordDO.class);
            recordDO.setPassbackParams(id + "");
            //保存数据
            alipayRecordService.save(recordDO);

            return R.ok().put("data", "true");
        }

        return R.error(1, "添加失败");
    }
}
