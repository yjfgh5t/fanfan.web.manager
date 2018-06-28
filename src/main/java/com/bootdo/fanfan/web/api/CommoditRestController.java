package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;
import com.bootdo.fanfan.vo.APICommoditySimpleVO;
import com.bootdo.fanfan.vo.APICommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询单个商品
     * @return
     */
    @GetMapping("/{id}")
    public R getItem(@PathVariable("id") Integer id){

        //查询商品详情
        CommoditDO model = commoditService.get(id);

        if(baseModel.getClientType().equals("Android")){
            return R.ok().put("data", mapper.map(model, APICommodityVO.class));
        }else {
            return R.ok().put("data", mapper.map(model, APICommoditySimpleVO.class));
        }
    }

    /**
     * 保存商品
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APICommodityVO commodityVO){

        CommoditDO commodit = mapper.map(commodityVO,CommoditDO.class);

        if(commodit.getId()==0) {
            commodit.setCustomerId(baseModel.getCustomerId());
            commodit.setDelete(0);
            commodit.setId(commoditService.save(commodit));
        }else {
            commoditService.update(commodit);
        }

        return R.ok().put("data",commodit.getId());
    }

    @PostMapping("/pullOffShelves/{id}")
    public R pullOffShelves(@PathVariable("id") Integer id,boolean pullOffShelves){

        //修改商品状态
        commoditService.updateStatus(id,pullOffShelves);

        return R.ok().put("data",true);
    }

}
