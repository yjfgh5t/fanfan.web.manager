package wyx.manager.vo;

import lombok.Data;

@Data
public class ShareStatisticVO {

    //分享标题
    private String sTitle;

    //分享描述
    private String sDescribe;

    //分享缩列图
    private String iconSrc;

    /**
     * 查看次数
     */
    private Integer sSee;

    /**
     * 点击次数
     */
    private Integer sClick;
}
