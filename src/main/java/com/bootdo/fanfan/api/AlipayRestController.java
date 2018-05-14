package com.bootdo.fanfan.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.fanfan.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/alipay")
public class AlipayRestController extends ApiBaseRestController {

    @Autowired
    AlipayManager alipayManager;

    @Autowired
    EMapper eMapper;

    @Autowired
    AlipayRecordService alipayRecordService;

    @Autowired
    OrderStateService orderStateService;

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
            alipayRecordService.save(recordDO);
        }catch (SecurityException ex){

        }

        return "success";
    }

    @GetMapping("/{id}")
    public R queryPayState(@PathVariable("id") Integer id,String orderNum){

        boolean hasPay = orderStateService.orderHasPay(id);
        if(hasPay){
            return  R.ok().put("data",true);
        }

        //查询支付宝订单
        AlipayTradeQueryResponse tradeQueryResponse = alipayManager.queryTradePay(orderNum);

        //判断是否支付成功
        if(tradeQueryResponse!=null && tradeQueryResponse.isSuccess()){
            return  R.ok().put("data",tradeQueryResponse.getTradeNo().equals(orderNum));
        }

        return R.ok().put("data",false);

    }
}
