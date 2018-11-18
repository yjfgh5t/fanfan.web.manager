package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.fanfan.domain.CommodityWidthExtendDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.CommodityCategoryService;
import com.bootdo.fanfan.service.CommodityService;
import com.bootdo.fanfan.vo.APICommodityCategoryRequVO;
import com.bootdo.fanfan.vo.APICommoditySimpleVO;
import com.bootdo.fanfan.vo.APICommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/commodityCategory")
public class CommodityCategoryRestController extends ApiBaseRestController  {

    @Autowired
    CommodityCategoryService commodityCategoryService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    private EMapper mapper;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/")
    public R list(){

        List<CommodityCategoryDO> categoryDOList = commodityCategoryService.getByCustomerId(getBaseModel().getCustomerId());

        List<APICommodityCategoryRequVO> commodityCategoryList =  mapper.mapArray(categoryDOList, APICommodityCategoryRequVO.class);

        return R.ok().put("data", commodityCategoryList);
    }


    /**
     * 保存商品
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody CommodityCategoryDO commoditCategory){

        if(commoditCategory.getId()==-1){
            commoditCategory.setCustomerId(getBaseModel().getCustomerId());
            commoditCategory.setDelete(0);
            commodityCategoryService.save(commoditCategory);
        }else {
            commodityCategoryService.update(commoditCategory);
        }
        return R.ok().put("data",commoditCategory.getId());
    }

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){

        //查询改分类下是否包含商品
        Map<String,Object> params = new HashMap<>();
        params.put("customerId",getBaseModel().getCustomerId());
        params.put("categoryId",id);
        int count = commodityService.count(params);
        if(count>0){
            return R.error(1,"请先移除改分类下的商品");
        }

        commodityCategoryService.remove(id);
        return R.ok();
    }
}
