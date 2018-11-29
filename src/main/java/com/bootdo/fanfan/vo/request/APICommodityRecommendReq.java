package com.bootdo.fanfan.vo.request;

import lombok.Data;

import java.util.List;

@Data
public class APICommodityRecommendReq {

    /**
     * 删除单个
     */
    private Integer deleteId;

    /**
     * 更新所有
     */
    private Integer [] commodityIds;
}
