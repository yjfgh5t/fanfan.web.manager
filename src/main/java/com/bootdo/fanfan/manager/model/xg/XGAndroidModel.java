package com.bootdo.fanfan.manager.model.xg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * @author: JY
 * @date: 2018/11/19 11:12
 */
@Data
public class XGAndroidModel {

    @JSONField(name="custom_content")
    private Map<String,Object> customContent;

    /**
     * 通知消息对象的唯一标识（只对信鸽通道生效）
     1）大于0：会覆盖先前相同id的消息
     2）等于0：展示本条通知且不影响其他消息
     3）等于-1：将清除先前所有消息，仅展示本条消息
     */
    @JSONField(name = "n_id")
    private Integer nId;

    /**
     * 本地通知样式标识
     * 默认值0
     */
    @JSONField(name = "builder_id")
    private Integer builderId;

    /**
     * 是否有铃声
     * 1）0：没有铃声
     * 2）1：有铃声
     * 默认1
     */
    private Integer ring;

    /**
     * 指定Android工程里raw目录中的铃声文件名，不需要后缀名
     */
    @JSONField(name = "ring_raw")
    private String ringRaw;

    /**
     * 是否使用震动
     * 1）0：没有震动
     * 2）1：有震动
     * 默认 1
     */
    private Integer vibrate;

    /**
     * 是否使用呼吸灯
     * 1）0：不使用呼吸灯
     * 2）1：使用呼吸灯
     * 默认1
     */
    private Integer lights;

    /**
     * 通知栏是否可清除
     * 0:不可清除
     * 1：可清除
     */
    private Integer clearable;

    /**
     * 通知栏图标是应用内图标还是上传图标
     * 1）0：应用内图标
     * 2）1：上传图标
     * 默认 0
     */
    @JSONField(name = "icon_type")
    private Integer iconType;

    /**
     * 应用内图标文件名或者下载图标的url地址
     */
    @JSONField(name ="icon_res")
    private String iconRes;

    /**
     * 设置是否覆盖指定编号的通知样式
     * 0:否
     * 1：是
     * 默认1
     */
    @JSONField(name = "style_id")
    private Integer styleId;

    /**
     * 消息在状态栏显示的图标，若不设置，则显示应用图标
     */
    @JSONField(name = "small_icon")
    private String smallIcon;

    /**
     * 设置点击通知栏之后的行为，默认为打开app
     */
    @JSONField(name = "action")
    private String action;

    public XGAndroidModel(){
        this.nId = 0;
        this.builderId =0;
        this.ring =1;
        this.vibrate =1;
        this.lights = 1;
        this.clearable=1;
        this.iconType=0;
        this.styleId=1;
    }
}
