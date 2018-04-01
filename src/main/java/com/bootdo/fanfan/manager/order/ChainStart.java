package com.bootdo.fanfan.manager.order;

import com.bootdo.fanfan.vo.APIOrderRequVO;

import java.util.ArrayList;
import java.util.List;

public class ChainStart {

    List<OrderChain> orderChainList;

    //读取长度
    private int loadSize=0;

    public ChainStart(){
        orderChainList=new ArrayList<>();
    }

    public void AddChain(OrderChain orderChain){
        orderChainList.add(orderChain);
    }

    public void Start(APIOrderRequVO orderRequVO){

    }

}
