package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommodityDO;
import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.CommodityService;
import com.bootdo.fanfan.service.CommodityExtendService;
import com.bootdo.fanfan.vo.APICommoditySimpleVO;
import com.bootdo.fanfan.vo.APICommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/commodity")
public class CommodityRestController extends ApiBaseRestController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private EMapper mapper;

    @Autowired
    CommodityExtendService commodityExtendService;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/")
    public R commodity(){

        Map<String, Object> _map = new HashMap<>();
        _map.put("sort","`order`");
        _map.put("customerId",getBaseModel().getCustomerId());

        List<CommodityWidthExtendDO> list = commodityService.listExtend(_map);
        if(getBaseModel().getClientType() == PlatformEnum.CustomerAndroid){
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
        CommodityWidthExtendDO model = commodityService.getExtend(id);

        if(getBaseModel().getClientType()==PlatformEnum.CustomerAndroid){
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

        CommodityDO commodit = mapper.map(commodityVO,CommodityDO.class);
        if(commodit.getId()==0) {
            commodit.setCustomerId(getBaseModel().getCustomerId());
            commodit.setId(commodityService.save(commodit));
        }else {
            commodityService.update(commodit);
        }

        if(commodityVO.getExtendList()!=null && commodityVO.getExtendList().size()>0){
            List<CommodityExtendDO> list = mapper.mapArray(commodityVO.getExtendList(),CommodityExtendDO.class);
            //获取老的id
            List<Integer> oldIds =  list.stream().filter((item)->item.getId()>0).map(CommodityExtendDO::getId).collect(Collectors.toList());
            //获取添加的
            List<CommodityExtendDO> newDatas = list.stream()
                    .filter((item)->{
                        //设置商品Id
                        item.setCommodityId(commodit.getId());
                        //返回id等于0的数据
                        return item.getId()==0;
                    })
                    .collect(Collectors.toList());
            //清除客户端移除的规格
            if(oldIds!=null && oldIds.size()>0){
                commodityExtendService.updateDeletes(oldIds,commodit.getId());
            }
            //保存数据
            if(newDatas!=null && newDatas.size()>0){
                newDatas.forEach((item)->{
                    commodityExtendService.save(item);
                });
            }
        }

        return R.ok().put("data",commodit.getId());
    }

    /**
     * 商品下架
     * @param id
     * @param pullOffShelves
     * @return
     */
    @PostMapping("/pullOffShelves/{id}")
    public R pullOffShelves(@PathVariable("id") Integer id,boolean pullOffShelves){

        //修改商品状态
        commodityService.updateStatus(id,pullOffShelves);

        return R.ok().put("data",true);
    }

}
