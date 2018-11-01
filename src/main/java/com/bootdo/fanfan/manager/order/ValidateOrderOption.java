package com.bootdo.fanfan.manager.order;

import com.bootdo.common.exception.BDException;
import com.bootdo.fanfan.domain.OrderChainDO;
import com.bootdo.fanfan.vo.APIOrderRequVO;
import com.bootdo.fanfan.vo.APIOrderRespVO;

public class ValidateOrderOption implements OrderChain {
    @Override
    public APIOrderRequVO beforeOrder(APIOrderRequVO orderRequVO) {

        if(orderRequVO==null){
            throw new BDException("订单信息不能为空",BDException.VERIFY_ERROR_CODE);
        }

        if(orderRequVO.getDetailList()==null || orderRequVO.getDetailList().size()==0){
            throw new BDException("订单商品信息不能为空",BDException.VERIFY_ERROR_CODE);
        }

        if(orderRequVO.getUserId()==null){
            throw  new BDException("用户信息不能为空",BDException.VERIFY_ERROR_CODE);
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
