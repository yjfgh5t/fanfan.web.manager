package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;
import com.bootdo.fanfan.vo.APICommoditySimpleVO;
import com.bootdo.fanfan.vo.APICommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/commodity")
public class CommoditRestController extends ApiBaseRestController {

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
        _map.put("customerId",baseModel.getCustomerId());

        List<CommoditDO> list = commoditService.list(_map);
        if(baseModel.getClientType().equals("Android")){
            return R.ok().put("data", mapper.mapArray(list, APICommodityVO.class));
        }else {
            return R.ok().put("data", mapper.mapArray(list, APICommoditySimpleVO.class));
        }
    }

}
