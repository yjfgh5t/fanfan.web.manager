package com.bootdo.fanfan.vo;

import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import lombok.Data;

import java.util.List;

@Data
public class APICommodityCategoryRequVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序号
     */
    private Integer order;

    /**
     * 商品列表
     */
    private List<APICommoditySimpleVO> commodityList;
}
