package com.bootdo.fanfan.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: JY
 * @date: 2018/11/7 10:37
 */
@Data
public class APIAuthorizeVO {

    //主键
    private Integer id;
    //认证状态
    private Integer authorizeState;
    //审核状态
    private Integer identificationState;
    //收款账号
    private String payeeId;
    //收款人姓名
    private String payeeName;
    //营业执照
    private String businessLicensePhoto;
    //营业执照截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date businessLicenseDate;
    //身份证照
    private String idCardPhoto;
    //店铺门面照
    private String shopPhoto;
    //审核失败原因
    private String failRemark;
    //认证成功地址
    private String identificationUrl;
    //商户Id
    private Integer customerId;

    /**
     * 授权地址
     */
    private String authorizeUrl;

}
