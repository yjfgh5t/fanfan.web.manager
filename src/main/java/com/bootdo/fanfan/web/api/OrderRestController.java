package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.DTO.OrderStatisticsDTO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderPayType;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.domain.enumDO.OrderTypeEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.manager.XGPushManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.OrderStateService;
import com.bootdo.fanfan.vo.*;
import com.bootdo.fanfan.vo.model.XGPushModel;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/order")
public class OrderRestController extends ApiBaseRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderStateService orderStateService;

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    AlipayRecordService alipayRecordService;

    @Autowired
    private EMapper mapper;

    @Autowired
    XGPushManager xgPushManager;

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/")
    public R createOrder(@RequestBody APIOrderRequVO orderModel) {
        try {

            orderModel.setCustomerId(getBaseModel().getCustomerId());

            //创建订单
            Integer orderId = orderService.createOrder(orderModel);

            //查询订单
            APIOrderRequVO requVO = orderService.queryOrder(orderId);

            return R.ok().put("data", requVO);
        } catch (SecurityException ex) {
            return R.error(1, ex.getMessage());
        }
    }

    /**
     * 订单计算价格
     * @param orderModel
     * @return
     */
    @PostMapping("/calculate")
    public R calculateOrder(@RequestBody APIOrderRequVO orderModel){
        //计算订单价格
        orderModel = orderService.calculateOrder(orderModel);

        return R.ok().put("data",orderModel);
    }

    /**
     * 查询用户订单列表
     *
     * @param orderQueryRequVO
     * @return
     */
    @PostMapping("/query")
    public R queryOrder(@RequestBody APIOrderQueryRequVO orderQueryRequVO) {

        if (orderQueryRequVO.getPageIndex() == null)
            return R.error("参数不能未空");

        int offSet = (orderQueryRequVO.getPageIndex() -1) * orderQueryRequVO.getPageSize();

        //设置参数
        Map<String, Object> params = new HashMap<>();

        //用户
        if (orderQueryRequVO.getUserId() != null) {
            params.put("userId", orderQueryRequVO.getUserId());
        }

        //商户
        if (orderQueryRequVO.getCustomerId() != null) {
            params.put("customerId", orderQueryRequVO.getCustomerId());
        }

        //状态
        if (orderQueryRequVO.getOrderState() != null) {
            params.put("orderState", orderQueryRequVO.getOrderState());
        }

        params.put("offset", offSet);
        params.put("limit", orderQueryRequVO.getPageSize());

        //参数数量不够
        if (params.size() < 3) {
            return R.error("参数不能未空");
        }

        List<APIOrderListVO> list = orderService.queryOrderByUser(params);

        return R.ok().put("data", list);
    }

    /**
     * 查询当日订单
     *
     * @param date 日期
     * @return
     */
    @PostMapping("/query/{date}")
    public R queryDayOrder(@PathVariable("date") @DateTimeFormat(pattern = "YYYY-MM-dd") Date date, APIOrderDayQueryRequVO dayQueryRequVO) {

        //时间为1970-01-01 则设为当前时间
        if(dayQueryRequVO.getNewOrder()){
            date = new Date();
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
        }

        dayQueryRequVO.setDate(date);

        Map<String, Object> params = new HashMap<>();
        //商户
        params.put("customerId", getBaseModel().getCustomerId());
        //开始日期
        params.put("startTime", date);
        //结束日期
        Date endDate = DateUtils.addDays(date, 1);
        params.put("endTime", endDate);
        params.put("lastId", dayQueryRequVO.getLastId());
        params.put("isMax", dayQueryRequVO.getIsMax());
        //订单状态不为空
        if (dayQueryRequVO.getOrderState() != null && dayQueryRequVO.getOrderState() > 0) {
            switch (dayQueryRequVO.getOrderState()) {
                case 1:
                    params.put("orderState", OrderStateEnum.userPaid.getVal());
                    break;
                case 2:
                    params.put("orderState", OrderStateEnum.orderSuccess.getVal());
                    break;
                case 3:
                    params.put("orderState", OrderStateEnum.businessCancel.getVal());
                    break;
                default:
            }
        }

        List<APIOrderListCustomerVO> listCustomerVOS = orderService.queryOrderByCustomer(params);

        return R.ok().put("data", listCustomerVOS);
    }

    /**
     * 查询订单统计信息
     * @param date
     * @return
     */
    @GetMapping("/statistics/{date}")
    public R queryStatistics(@PathVariable("date") @DateTimeFormat(pattern = "YYYY-MM-dd") Date date){

        APIOrderStatisticsVO result = new APIOrderStatisticsVO();

        Map<String,Object> params = new HashMap<>();
        params.put("customerId",getBaseModel().getCustomerId());
        //开始日期
        params.put("startTime",date);
        //结束日期
        Date endDate = DateUtils.addDays(date, 1);
        params.put("endTime",endDate);
        //查询状态
        Integer[] states = new Integer[]{OrderStateEnum.userPaid.getVal(),OrderStateEnum.orderSuccess.getVal()};
        params.put("stateArray",states);

        //查询成功的订单数
        OrderStatisticsDTO statisticsDTO = orderService.queryOrderStatistics(params);
        result.setOrderCount(statisticsDTO.getOrderCount());
        result.setOrderTotal(statisticsDTO.getOrderTotal());

        //查询取消的订单数
        states = new Integer[]{OrderStateEnum.businessCancel.getVal()};
        params.put("stateArray",states);
        statisticsDTO = orderService.queryOrderStatistics(params);
        result.setCancelOrderCount(statisticsDTO.getOrderCount());
        result.setCancelOrderTotal(statisticsDTO.getOrderTotal());

        return R.ok().put("data",result);
    }

    /**
     * 查询单个订单详情
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public R orderDetail(@PathVariable("orderId") Integer orderId) {

        //查询订单
        APIOrderRequVO requVO = orderService.queryOrder(orderId);

        if (requVO == null)
            return R.error("订单不存在");

        //设置状态文本
        requVO.setOrderStateText(OrderStateEnum.get(requVO.getOrderState()).getText());
        requVO.setOrderTypeText(OrderTypeEnum.get(requVO.getOrderType()).getText());

        return R.ok().put("data", requVO);
    }

    //region 订单状态处理

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("/state/{orderId}")
    public R orderState(@PathVariable("orderId") Integer orderId, String state,String customerRemark) {

        OrderDO orderDO = new OrderDO();
        orderDO.setId(orderId);

        OrderStateEnum stateEnum = null;

        switch (state) {
            //用户取消订单
            case "user-cancel":
                stateEnum = OrderStateEnum.userCancel;
                break;
            //商户取消订单
            case "business-cancel":
                stateEnum = OrderStateEnum.businessCancel;
                break;
            //商户开始处理订单
            case "business-confirm":
                stateEnum = OrderStateEnum.businessConfirm;
                break;
            //订单处理完成
            case "order-completed":
                stateEnum = OrderStateEnum.orderSuccess;
                break;
            default:
                return R.error(1, "非法参数");
        }

        //商家描述
        if(StringUtils.isNotEmpty(customerRemark)){
            orderDO.setOrderCustomerRemark(customerRemark);
        }

        //更新状态
        orderDO.setOrderState(stateEnum.getVal());
        orderService.updateOrderState(orderDO);

        //返回状态
        Map<String, Object> data = new HashMap<>();
        data.put("orderStateText", stateEnum.getText());
        data.put("orderState", stateEnum.getVal());

        return R.ok().put("data", data);
    }

    //endregion


    /**
     * 验证是否支付
     *
     * @param id
     * @return
     */
    @PostMapping("/checkPay/{id}")
    public R queryPayState(@PathVariable("id") Integer id) {

        boolean hasPay = orderStateService.orderHasPay(id);
        if (hasPay) {
            return R.ok().put("data", true);
        }
        OrderDO orderDO = orderService.get(id);
        //查询支付宝订单
        AlipayTradeQueryResponse tradeQueryResponse = alipayManager.queryTradePay(orderDO.getOrderNum(),orderDO.getCustomerId());

        //判断是否支付成功
        if (tradeQueryResponse != null && tradeQueryResponse.isSuccess()) {
            AlipayRecordDO recordDO = mapper.map(tradeQueryResponse, AlipayRecordDO.class);
            recordDO.setPassbackParams(id + "");
            recordDO.setBuyerId(tradeQueryResponse.getBuyerUserId());
            //保存数据
            alipayRecordService.save(recordDO);

            return R.ok().put("data", true);
        }

        return R.ok().put("data", false);
    }

    /**
     * 获取订单状态
     *
     * @param idArry
     * @return
     */
    @GetMapping("/states")
    public R getState(List<Integer> idArry) {

        List<OrderDO> orderDoArray = orderService.getStateById(idArry);

        if (orderDoArray != null && orderDoArray.size() > 0) {
            List<APIOrderListVO> orderArray = mapper.mapArray(orderDoArray, APIOrderListVO.class);

            if (orderArray != null && orderArray.size() > 0) {
                orderArray.forEach((item) -> {
                    //设置订单状态字符
                    item.setOrderStateText(OrderStateEnum.get(item.getId()).getText());
                });
                return R.ok().put("data", orderArray);
            }
        }
        return R.ok().put("data", "");
    }


    //region debug

    /**
     * 测试推送订单
     *
     * @param id
     * @return
     */
    @GetMapping("/debugPush/{id}")
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
        pushModel.addParams("data", data);
        pushModel.addParams("time",Calendar.getInstance().getTime());

        //推送消息
        xgPushManager.put(pushModel);
        return R.ok();
    }

    @GetMapping("/debugPay/{id}")
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
    //endregion
}
