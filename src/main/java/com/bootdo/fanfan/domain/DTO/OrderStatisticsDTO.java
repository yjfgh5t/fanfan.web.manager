package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: JY
 * @date: 2018/10/25 14:11
 */
@Data
public class OrderStatisticsDTO {

    private BigDecimal orderTotal;

    private Integer orderCount;

}
