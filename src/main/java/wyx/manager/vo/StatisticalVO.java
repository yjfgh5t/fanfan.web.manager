package wyx.manager.vo;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class StatisticalVO {

    /**
     * shareid
     */
    private Integer sid;

    /**
     * 查看次数
     */
    private Integer sSee;

    /**
     * 点击次数
     */
    private Integer sClick;

    /**
     * 创建时间
     */
    private DateTime createDate;
}
