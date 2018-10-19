package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: JY
 * @date: 2018/10/19 17:11
 */
@Data
public class APICommodityExtendVO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 商品id
     */
    private Integer commodityId;
    /**
     * 商品价格
     */
    private BigDecimal commodityPrice;
    /**
     * 类型 [1:规格]
     */
    private Integer type;

    /**
     * 名称
     */
    private String title;
}
