package com.bootdo.fanfan.vo.view;

import lombok.Data;

/**
 * @author: JY
 * @date: 2018/5/17 10:31
 */
@Data
public class DictionaryVO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 1011:营业开始时间  1012:营业结束时间   1021:店铺名称  1022:最低起送价
     */
    private Integer key;

    /**
     * Key文本
     */
    private String keyText;

    /**
     * 值
     */
    private String val;

    /**
     * 创建人
     */
    private Integer createId;
}
