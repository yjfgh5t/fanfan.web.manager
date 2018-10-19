package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class APICommoditySimpleVO {

    private Integer id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 活动
     */
    private List<String> active;

    /**
     * 商品原价
     */
    private BigDecimal price;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 图片
     */
    private String icon;

    /**
     * 描述
     */
    private String desc;

    /**
     * 类别id
     */
    private String categoryId;

    /**
     * 单位
     */
    private String unit;

    /**
     * 剩余量
     */
    private Integer surplusSize;

    /**
     * 扩展信息
     */
    private List<APICommodityExtendVO> extendList;
}
