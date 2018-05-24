package com.bootdo.fanfan.manager.order;

import com.bootdo.fanfan.domain.OrderChainDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import com.bootdo.fanfan.vo.APIOrderRespVO;

public class ValidateOrderOption implements OrderChain {
    @Override
    public APIOrderRequVO beforeOrder(APIOrderRequVO orderRequVO) {

        if(orderRequVO==null){
            throw new SecurityException("订单信息不能为空");
        }

        if(orderRequVO.getDetailList()==null || orderRequVO.getDetailList().size()==0){
            throw new SecurityException("订单商品信息不能为空");
        }

        if(orderRequVO.getUserId()==null){
            throw  new SecurityException("用户信息不能为空");
        }

        if(orderRequVO.getOrderState()!=null || orderRequVO.getOrderState()==101 ){
            return null;
        }

        return null;
    }

    @Override
    public APIOrderRespVO afterOrder(OrderChainDO orderChainDO) {
        return null;
    }
}
