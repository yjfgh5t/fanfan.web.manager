package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/8/31 10:53
 */
@Data
public class DeskDTO {

    //主键
    private Integer id;
    //桌号
    private String title;
    //商户id
    private Integer customerId;
    //二维码Id
    private String qrCodeId;


}
