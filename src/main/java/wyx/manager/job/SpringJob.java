package wyx.manager.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wyx.manager.service.WyxStatisticalServiceI;

@Component
public class SpringJob {

    private static final Logger logger = Logger.getLogger(SpringJob.class);

    @Autowired
    WyxStatisticalServiceI wyxStatisticalService;

    /**
     * 统计数据持久化 每个小时执行一次
     */
    @Scheduled(cron = "0 0 0-23 * * ?")
    public  void StatisticPersistence(){

        int count  = wyxStatisticalService.updateCacheData();

        logger.info(String.format("统计数据持久化成功数%d",count));
    }

}
