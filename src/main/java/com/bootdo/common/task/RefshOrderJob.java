package com.bootdo.common.task;

import com.bootdo.common.aspect.WebLogAspect;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.OrderStateService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/5/10 16:05
 */
public class RefshOrderJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    OrderStateService orderStateService;

    @Autowired
    OrderService orderService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calendar nowTime = Calendar.getInstance();

        //将时间提前14分50秒
        nowTime.set(Calendar.MINUTE,nowTime.get(Calendar.MINUTE)-15);
        nowTime.set(Calendar.SECOND,nowTime.get(Calendar.SECOND)+10);

        //查询失效的订单
        Map<Integer,Date> awaitPayOrder = orderStateService.getAwaitPayOrder();

        if(awaitPayOrder.size()>0) {

            //遍历数据
            awaitPayOrder.forEach((key, val) -> {

                OrderDO orderDO = new OrderDO();
                orderDO.setOrderState(OrderStateEnum.userPayOvertime.getVal());
                orderDO.setId(key);

                //设置订单超时未支付
                orderService.updateOrderState(orderDO);
            });
        }
    }
}
