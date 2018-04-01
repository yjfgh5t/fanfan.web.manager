package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class APICommodityVO {

    private Integer id;

    private String title;

    private List<String> active;

    private BigDecimal price;

    private BigDecimal salePrice;

    private String icon;

    private String desc;

    /**
     * 剩余量
     */
    private Integer surplusSize;
}
