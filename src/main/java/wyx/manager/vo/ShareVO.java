package wyx.manager.vo;

import lombok.Data;

@Data
public class ShareVO {

    //分享标题
    private String sTitle;

    //分享描述
    private String sDescribe;

    //分享缩列图
    private String iconSrc;

    //创建id
    private String createId;

    //口令
    private String tpToken;
}
