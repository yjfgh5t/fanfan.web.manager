package com.bootdo.fanfan.domain;

import lombok.Data;

import java.util.List;

/**
 * @author: JY
 * @date: 2018/10/19 17:38
 */
@Data
public class CommodityWidthExtendDO extends CommodityDO {

    private List<CommodityExtendDO> extendList;
}
