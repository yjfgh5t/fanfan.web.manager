package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class APIPrintOrderDetailVO {
    private String outTitle;

    private BigDecimal outPrice;

    //商品数量
    private Integer outSize;

    private Integer outType;
}
