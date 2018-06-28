package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class APICommoditySimpleVO {

    private Integer id;

    private String title;

    private List<String> active;

    private BigDecimal price;

    private BigDecimal salePrice;

    private String icon;

    private String desc;

    private String categoryId;

    private String unit;

    /**
     * 剩余量
     */
    private Integer surplusSize;
}
