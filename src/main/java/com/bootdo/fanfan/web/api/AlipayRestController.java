package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/alipay")
public class AlipayRestController {

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    EMapper eMapper;

    @Autowired
    AlipayRecordService alipayRecordService;

    Log log = LogFactory.getLog(AlipayRestController.class);

    /**
     * 接收支付宝预付单消息
     * @return
     */
    @RequestMapping("/alipayReceiver")
    public String alipayReceiver(HttpServletRequest request){

        //获取参数
        Map<String,String> mapParams = new HashMap<>(request.getParameterMap().size()),convertParams = new HashMap<>(request.getParameterMap().size());
 
         //数据转换
         request.getParameterMap().forEach((key,val)->{
             mapParams.put(key,val[0]);
             convertParams.put(StringUtils.lineToHump(key),val[0]);
         });

       if(!alipayManager.checkSign(mapParams)) {
           System.out.println("验证签名失败"+ JSON.toJSONString(mapParams));
           return "failure";
       }

       //转换
       AlipayRecordDO recordDO = eMapper.map(convertParams, AlipayRecordDO.class);

       //执行保存
        try {
            //修改订单支付状态
            alipayRecordService.save(recordDO);
        }catch (Exception ex){
            log.error("保存支付宝异步消息异常--{}",ex);
        }

        return "success";
    }
}
