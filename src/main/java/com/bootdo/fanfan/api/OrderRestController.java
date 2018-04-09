package com.bootdo.fanfan.api;

import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.vo.APIOrderListVO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/order")
public class OrderRestController {

    @Autowired
    OrderService orderService;



    /**
     * 创建订单
     * @return
     */
    @PostMapping("/")
    public R order(@RequestBody APIOrderRequVO orderModel){
        try {
            //创建订单
            String  orderNum = orderService.createOrder(orderModel);

            //查询订单
            APIOrderRequVO requVO = orderService.queryOrder(orderNum);

            return R.ok().put("data",requVO);
        }catch (SecurityException ex){
            return R.error(1,ex.getMessage());
        }
    }

    @GetMapping("/")
    public R order(Integer userId,Integer pageIndex){

        if(userId==null ||  pageIndex==null)
            return R.error("参数不能未空");

        int page =  pageIndex*10;

        //设置参数
        Map<String,Object> params = new HashMap<>();
        params.put("createId",userId);
        params.put("offset",page-10);
        params.put("limit",page);

        List<APIOrderListVO> list = orderService.queryOrderByUser(params);

        if(list!=null && list.size()>0){
            list.forEach((item)->{
                item.setOrderStateText(OrderStateEnum.get(item.getOrderState()).getText());
                item.setCommoditImg("http://m.wxcard.com.cn/"+item.getCommoditImg());
            });
        }

       return R.ok().put("data",list);
    }

    @GetMapping("/{orderNum}")
    public R orderDetail(@PathVariable("orderNum") String orderNum){

        //查询订单
        APIOrderRequVO requVO = orderService.queryOrder(orderNum);

        if(requVO==null)
            return R.error("订单不存在");

        //设置状态文本
        requVO.setOrderStateText(OrderStateEnum.get(requVO.getOrderState()).getText());

        return R.ok().put("data",requVO);
    }

}
