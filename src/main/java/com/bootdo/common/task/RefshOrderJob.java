package com.bootdo.common.task;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.common.extend.EMapper;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.DTO.OrderRefreshDTO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderService;
import com.bootdo.fanfan.service.OrderStateService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author: JY
 * @date: 2018/5/10 16:05
 */
public class RefshOrderJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(RefshOrderJob.class);

    @Autowired
    OrderStateService orderStateService;

    @Autowired
    OrderService orderService;

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    private EMapper mapper;

    @Autowired
    private AlipayRecordService alipayRecordService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Exception exception=null;

        try {
            //查询失效的订单
            Map<Integer, OrderRefreshDTO> awaitPayOrder = orderStateService.getAwaitPayOrder();

            if (awaitPayOrder.size() == 0) {
                return;
            }

            Calendar nowTime = Calendar.getInstance();

            //将时间提前14分50秒
            nowTime.set(Calendar.MINUTE, nowTime.get(Calendar.MINUTE) - 15);

            Iterator<Map.Entry<Integer, OrderRefreshDTO>> iterator = awaitPayOrder.entrySet().iterator();

            //读取数据
            while (iterator.hasNext()) {
                try {
                    Map.Entry<Integer, OrderRefreshDTO> entry = iterator.next();

                    OrderRefreshDTO model = entry.getValue();

                    //查询是否已经支付
                    AlipayTradeQueryResponse tradeQueryResponse = alipayManager.queryTradePay(model.getOrderNum());

                    //已经支付
                    if (tradeQueryResponse != null && tradeQueryResponse.isSuccess()) {
                        //保存数据
                        AlipayRecordDO recordDO = mapper.map(tradeQueryResponse, AlipayRecordDO.class);
                        alipayRecordService.save(recordDO);
                    } else if (model.getCreateTime().getTime() < nowTime.getTime().getTime()) {
                        OrderDO orderDO = new OrderDO();
                        orderDO.setOrderState(OrderStateEnum.userPayOvertime.getVal());
                        orderDO.setId(entry.getKey());

                        //设置订单超时未支付
                        orderService.updateOrderState(orderDO);
                    }
                }catch (Exception ex){
                    exception=ex;
                }
            }
        }catch (Exception ex){
            exception=ex;
        }

        //抛出异常
        if(exception!=null){
            throw new JobExecutionException(exception);
        }
    }
}
