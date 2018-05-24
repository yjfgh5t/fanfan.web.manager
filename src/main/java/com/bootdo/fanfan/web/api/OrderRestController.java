package com.bootdo.fanfan.web.api;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.OrderStateService;
import com.bootdo.fanfan.vo.APIOrderListVO;
import com.bootdo.fanfan.vo.APIOrderQueryRequVO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private BootdoConfig bootdoConfig;

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/")
    public R createOrder(@RequestBody APIOrderRequVO orderModel){
        try {

            orderModel.setCustomerId(baseModel.getCustomerId());

            //创建订单
            Integer  orderId = orderService.createOrder(orderModel);

            //查询订单
            APIOrderRequVO requVO = orderService.queryOrder(orderId);

            return R.ok().put("data",requVO);
        }catch (SecurityException ex){
            return R.error(1,ex.getMessage());
        }
    }

    /**
     * 查询用户订单列表
     * @param orderQueryRequVO
     * @return
     */
    @PostMapping("/query")
    public R queryOrder(@RequestBody APIOrderQueryRequVO orderQueryRequVO){

        if(orderQueryRequVO.getPageIndex()==null)
            return R.error("参数不能未空");

        int page =  orderQueryRequVO.getPageIndex()*10;

        //设置参数
        Map<String,Object> params = new HashMap<>();

        //用户
        if(orderQueryRequVO.getUserId()!=null) {
            params.put("userId", orderQueryRequVO.getUserId());
        }

        //商户
        if(orderQueryRequVO.getCustomerId()!=null){
            params.put("customerId", orderQueryRequVO.getCustomerId());
        }

        //状态
        if(orderQueryRequVO.getOrderState()!=null){
            params.put("orderState", orderQueryRequVO.getOrderState());
        }

        params.put("offset",page-10);
        params.put("limit",page);

        //参数数量不够
        if(params.size()<3){
            return R.error("参数不能未空");
        }


        List<APIOrderListVO> list = orderService.queryOrderByUser(params);

       return R.ok().put("data",list);
    }

    /**
     * 查询单个订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public R orderDetail(@PathVariable("orderId") Integer orderId){

        //查询订单
        APIOrderRequVO requVO = orderService.queryOrder(orderId);

        if(requVO==null)
            return R.error("订单不存在");

        //设置状态文本
        requVO.setOrderStateText(OrderStateEnum.get(requVO.getOrderState()).getText());

        return R.ok().put("data",requVO);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @PostMapping("/cancel/{orderId}")
    public R orderCancel(@PathVariable("orderId") Integer orderId){

        OrderDO orderDO = new OrderDO();
        orderDO.setId(orderId);
        orderDO.setOrderState(OrderStateEnum.userCancel.getVal());

        orderService.updateOrderState(orderDO);

        return R.ok();
    }

    /**
     * 验证是否支付
     * @param id
     * @param recordDO
     * @return
     */
    @PostMapping("/checkPay/{id}")
    public R queryPayState(@PathVariable("id") Integer id,@RequestBody AlipayRecordDO recordDO){

        boolean hasPay = orderStateService.orderHasPay(id);
        if(hasPay){
            return  R.ok().put("data",true);
        }

        //查询支付宝订单
        AlipayTradeQueryResponse tradeQueryResponse = alipayManager.queryTradePay(recordDO.getTradeNo());

        //判断是否支付成功
        if(tradeQueryResponse!=null && tradeQueryResponse.isSuccess()){
            //保存数据
            alipayRecordService.save(recordDO);

            return  R.ok().put("data",true);
        }

        return R.ok().put("data",false);
    }

    /**
     * 获取订单状态
     * @param idArry
     * @return
     */
    @GetMapping("/states")
    public R getState(List<Integer> idArry){

        List<OrderDO>  orderDoArray = orderService.getStateById(idArry);

        if(orderDoArray!=null && orderDoArray.size()>0) {
            List<APIOrderListVO> orderArray = mapper.mapArray(orderDoArray, APIOrderListVO.class);

            if (orderArray != null && orderArray.size() > 0) {
                orderArray.forEach((item) -> {
                    //设置订单状态字符
                    item.setOrderStateText(OrderStateEnum.get(item.getId()).getText());
                });
                return R.ok().put("data", orderArray);
            }
        }
        return R.ok().put("data","");
    }

    /**
     * 测试推送订单
     * @param id
     * @return
     */
    @GetMapping("/debugPush/{id}")
    public R debugPush(@PathVariable("id") Integer id){
        orderService.sendOrderNotification(id);
        return R.ok();
    }
}
