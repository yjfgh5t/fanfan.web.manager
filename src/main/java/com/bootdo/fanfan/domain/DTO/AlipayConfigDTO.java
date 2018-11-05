package com.bootdo.fanfan.domain.DTO;

import com.bootdo.common.config.AlipayConfig;
import lombok.Data;

/**
 * @author: JY
 * @date: 2018/11/5 13:46
 */
@Data
public class AlipayConfigDTO {

    AlipayConfig alipayConfig;

    private String appId;

    private String token;

}
