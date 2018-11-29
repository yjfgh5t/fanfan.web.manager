package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.fanfan.domain.CommodityDO;
import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.CommodityCategoryService;
import com.bootdo.fanfan.service.CommodityService;
import com.bootdo.fanfan.service.CommodityExtendService;
import com.bootdo.fanfan.vo.APICommodityCategoryRequVO;
import com.bootdo.fanfan.vo.APICommodityRecommendVO;
import com.bootdo.fanfan.vo.APICommoditySimpleVO;
import com.bootdo.fanfan.vo.APICommodityVO;
import com.bootdo.fanfan.vo.request.APICommodityRecommendReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    CommodityCategoryService commodityCategoryService;

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
        if(getBaseModel().getClientEnumType() == PlatformEnum.CustomerAndroid){
            return R.ok().put("data", mapper.mapArray(list, APICommodityVO.class));
        }else {
            return R.ok().put("data", mapper.mapArray(list, APICommoditySimpleVO.class));
        }
    }

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/commodityWithCategory")
    public R commodityWithCategory(){

        Map<String, Object> results = new HashMap<>();

        Map<String, Object> _map = new HashMap<>();
        _map.put("sort","`order`");
        _map.put("customerId",getBaseModel().getCustomerId());
        //查询商品数据
        List<CommodityWidthExtendDO> list = commodityService.listExtend(_map);
        //返回类型
        if(getBaseModel().getClientEnumType() == PlatformEnum.CustomerAndroid){
            results.put("commodity",mapper.mapArray(list, APICommodityVO.class));
        }else {
            results.put("commodity",mapper.mapArray(list, APICommoditySimpleVO.class));
        }

        //商品类别对象
        List<CommodityCategoryDO> categoryDOList = commodityCategoryService.getByCustomerId(getBaseModel().getCustomerId());
        List<APICommodityCategoryRequVO> categoryList = mapper.mapArray(categoryDOList, APICommodityCategoryRequVO.class);
        results.put("category",categoryList);

        return R.ok().put("data",results);
    }

    /**
     * 查询单个商品
     * @return
     */
    @GetMapping("/{id}")
    public R getItem(@PathVariable("id") Integer id){

        //查询商品详情
        CommodityWidthExtendDO model = commodityService.getExtend(id);

        if(getBaseModel().getClientEnumType()==PlatformEnum.CustomerAndroid){
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

    /**
     * 推荐商品
     * @param request
     * @return
     */
    @PostMapping("/setRecommend")
    public R recommend(@RequestBody APICommodityRecommendReq request){
        if(request.getCommodityIds()!=null && request.getCommodityIds().length>6){
            return  R.error("推荐商品不能超过6个哦");
        }
        int result = -1;
        //修改商品状态
        if(request.getCommodityIds()!=null && request.getCommodityIds().length>0) {
             result = commodityService.setRecommend(request.getCommodityIds(), getBaseModel().getCustomerId());
        }else{
            result = commodityService.removeRecommend(request.getDeleteId());
        }

      if(result==0) {
          return R.error("设置失败，请重试");
      }else{
          return R.ok().put("data", true);
      }
    }

    /**
     * 获取推荐商品
     * @return
     */
    @GetMapping("/getRecommend")
    public R getRecommend(){
        //查询列表
        List<CommodityDO> recommendList = commodityService.getRecommend(getBaseModel().getCustomerId());
        //数据转换
        List<APICommodityRecommendVO> result = mapper.mapArray(recommendList, APICommodityRecommendVO.class);
        if(result==null){
            result = new ArrayList<>();
        }
        return R.ok().put("data",result);
    }

}
