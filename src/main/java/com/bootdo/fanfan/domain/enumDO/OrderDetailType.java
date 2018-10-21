package com.bootdo.fanfan.domain.enumDO;

import lombok.Getter;

@Getter
public enum OrderDetailType {
    /**
     * 商品
     */
    Commodity(1),
    /**
     * 滿減
     */
    FullSubtraction(2),
    /**
     * 折扣
     */
    Discount(3),
    /**
     *送优惠券
     */
    SendCoupon(4),
    /**
     * 商品规格
     */
    CommodityNorms(5);

    private Integer id;
    OrderDetailType(Integer id){
        this.id = id;
    }
}
