package com.bootdo.fanfan.vo;

import lombok.Data;

import java.util.List;

@Data
public class APIGDMapVO {

    private int status;

    private String info;

    private int count;

    private List<APIGDMapTipVO> tips;
}
