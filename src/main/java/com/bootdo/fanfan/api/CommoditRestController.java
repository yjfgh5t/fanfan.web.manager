package com.bootdo.fanfan.api;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;
import com.bootdo.fanfan.vo.APICommodityVO;
import com.bootdo.fanfan.vo.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/commodity")
public class CommoditRestController {

    @Autowired
    private CommoditService commoditService;

    @Autowired
    private EMapper mapper;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/")
    public R commodity(){

        Map<String, Object> _map = new HashMap<>();

        _map.put("sort","`order`");
        _map.put("order","asc");
        _map.put("limit","10");
        _map.put("offset","0");
        Query query =new Query(_map);
        List<APICommodityVO> list = mapper.mapArray(commoditService.list(query), APICommodityVO.class);
        return R.ok().put("data", list);
    }



}
