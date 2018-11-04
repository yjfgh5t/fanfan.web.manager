package com.bootdo.fanfan.domain.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class QRCodeDeskDTO {
    /**
     * Id
     */
    private String id;
    /**
     * CustomerId
     */
    private Integer customerId;
    /**
     * 客桌Id
     */
    private Integer deskId;
    /**
     * 客桌编号
     */
    private String deskText;
}
