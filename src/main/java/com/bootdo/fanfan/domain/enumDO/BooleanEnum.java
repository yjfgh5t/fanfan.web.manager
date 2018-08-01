package com.bootdo.fanfan.domain.enumDO;

import lombok.Getter;

/**
 * @author: JY
 * @date: 2018/7/30 17:08
 */
@Getter
public enum BooleanEnum {

    False(0,"False"),
    True(1,"OK"),;

    private Integer val;

    private String  text;

    BooleanEnum(Integer val,String text){
        this.text=text;
        this.val=val;
    }
}
