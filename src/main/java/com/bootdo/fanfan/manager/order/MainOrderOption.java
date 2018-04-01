package com.bootdo.fanfan.manager.order;

import com.bootdo.fanfan.domain.OrderChainDO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import com.bootdo.fanfan.vo.APIOrderRespVO;
import org.springframework.beans.factory.annotation.Autowired;

public class MainOrderOption implements OrderChain {

    @Autowired
    OrderService orderService;


    @Override
    public APIOrderRequVO beforeOrder(APIOrderRequVO orderRequVO) {
        return null;
    }

    @Override
    public APIOrderRespVO afterOrder(OrderChainDO orderChainDO) {
        return null;
    }
}
