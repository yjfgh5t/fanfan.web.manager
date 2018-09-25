package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author: JY
 * @date: 2018/7/23 10:16
 */
@Data
public class OrderRefreshDTO {

    private Integer orderId;

    private Date createTime;

    private String orderNum;

    private Integer customerId;
}
