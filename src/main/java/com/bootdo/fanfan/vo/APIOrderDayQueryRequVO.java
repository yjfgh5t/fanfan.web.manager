package com.bootdo.fanfan.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: JY
 * @date: 2018/7/30 18:13
 */
@Data
public class APIOrderDayQueryRequVO {

    /**
     * 查询的日期
     */
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date date;

    /**
     * 最后的id
     */
    private Integer lastId;

    /**
     * lastId是最大向下查询 或者最小向上查询
     */
    private Boolean isMax;

    /**
     * 查询新订单--默认为当日订单
     */
    private Boolean newOrder;

    /**
     * 分页数
     */
    private Integer pageSize;

    /**
     * 订单状态
     */
    private Integer orderState;

    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date lastOrderTime;
}
