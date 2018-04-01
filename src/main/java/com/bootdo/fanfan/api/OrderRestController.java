package com.bootdo.fanfan.api;

import com.bootdo.common.utils.R;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
