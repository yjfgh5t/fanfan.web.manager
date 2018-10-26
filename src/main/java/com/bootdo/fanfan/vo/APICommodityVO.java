package com.bootdo.fanfan.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class APICommodityVO {
    //标识id
    private Integer id;

    //商品分类
    private Integer categoryId;

    //商品标题
    private String title;

    //商品图片title
    private String icon;

    //商品原价
    private BigDecimal price;

    //商品售价
    private BigDecimal salePrice;

    //商品售价
    private BigDecimal packagePrice;

    //商品描述
    private String desc;

    //每天/每餐 商品库存量
    private Integer inventory;

    //商品单位
    private String unit;

    //1:有效 2:下架
    private Integer status;

    //排序 值越大 越靠前
    private Integer order;

    //商品类型 1：出售 2：非卖品
    private Integer saleType;

    //是否必点
    private Integer mustOrder;

    /**
     * 扩展信息
     */
    private List<APICommodityExtendVO> extendList;
}
