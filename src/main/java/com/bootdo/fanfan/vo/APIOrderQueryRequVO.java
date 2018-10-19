package com.bootdo.fanfan.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: JY 订单查询
 * @date: 2018/5/24 16:24
 */
@Data
public class APIOrderQueryRequVO {

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 分页数
     */
    private Integer pageIndex;

    /**
     * 分页条数
     */
    private Integer pageSize;

    /**
     * 商户Id
     */
    private Integer customerId;

    /**
     * 订单状态 多个
     */
    private List<Integer> orderState;

}
