package com.bootdo.fanfan.domain.enumDO;

import lombok.Getter;

/**
 * @author: JY
 * @date: 2018/11/7 17:17
 */
@Getter
public enum IdentificationEnum {

    NoIdentification(0,"未认证"),
    WaitIdentification(1,"认证中"),
    FailIdentification(2,"认证失败"),
    ConfirmIdentification(3,"认证成功待确认"),
    SuccessIdentification(4,"已认证");

    private Integer val;

    private String  text;

    IdentificationEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }
}
