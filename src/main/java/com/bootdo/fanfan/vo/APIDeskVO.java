package com.bootdo.fanfan.vo;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/7/3 12:24
 */
@Data
public class APIDeskVO {
    //主键
    private Integer id;
    //桌号
    private String title;
    //商户id
    private Integer customerId;
}
