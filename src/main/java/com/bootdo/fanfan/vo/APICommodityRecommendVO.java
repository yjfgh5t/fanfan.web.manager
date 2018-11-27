package com.bootdo.fanfan.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class APICommodityRecommendVO {

    private Integer id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 图片
     */
    private String icon;
}
