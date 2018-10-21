package com.bootdo.testDemo;

import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.service.CommodityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/10/19 17:58
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCommodity {

    @Autowired
    private CommodityService commodityService;


    @Test
    public void  list(){

        Map<String, Object> _map = new HashMap<>();
        _map.put("sort","`order`");
        _map.put("customerId",132);

        List<CommodityWidthExtendDO> list = commodityService.listExtend(_map);

        System.out.println(list.toString());
    }
}
