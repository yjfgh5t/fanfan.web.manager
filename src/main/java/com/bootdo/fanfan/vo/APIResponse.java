package com.bootdo.fanfan.vo;

import lombok.Data;

@Data
public class APIResponse {

    private int code;

    private String msg;

    private Object  data;


}
