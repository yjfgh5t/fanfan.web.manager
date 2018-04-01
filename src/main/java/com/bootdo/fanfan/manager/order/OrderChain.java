package com.bootdo.fanfan.manager.order;

import com.bootdo.fanfan.domain.OrderChainDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import com.bootdo.fanfan.vo.APIOrderRespVO;

public interface OrderChain {

    /**
     * 订单处理前
     * @return
     */
    APIOrderRequVO beforeOrder(APIOrderRequVO orderRequVO);

    /**
     * 订单处理后
     * @return
     */
    APIOrderRespVO afterOrder(OrderChainDO orderChainDO);

}
