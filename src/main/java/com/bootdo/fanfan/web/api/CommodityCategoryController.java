package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.CommoditCategoryDO;
import com.bootdo.fanfan.service.CommoditCategoryService;
import com.bootdo.fanfan.service.CommoditService;
import com.bootdo.fanfan.vo.APICommodityCategoryRequVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/commodityCategory")
public class CommodityCategoryController extends ApiBaseRestController  {

    @Autowired
    CommoditCategoryService commoditCategoryService;

    @Autowired
    CommoditService commoditService;

    @Autowired
    private EMapper mapper;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/")
    public R list(){

        Map<String,Object> parmas = new HashMap<>();
        parmas.put("sort","`order`");
        parmas.put("order","asc");
        parmas.put("delete","0");
        parmas.put("customerId",this.baseModel.getCustomerId());

        List<APICommodityCategoryRequVO> commoditCategoryList =  mapper.mapArray(commoditCategoryService.list(parmas), APICommodityCategoryRequVO.class);
        return R.ok().put("data", commoditCategoryList);
    }

    /**
     * 保存商品
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody CommoditCategoryDO commoditCategory){

        if(commoditCategory.getId()==-1){
            commoditCategory.setCustomerId(this.baseModel.getCustomerId());
            commoditCategoryService.save(commoditCategory);
        }else {
            commoditCategoryService.update(commoditCategory);
        }
        return R.ok();
    }

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){

        //查询改分类下是否包含商品
        Map<String,Object> params = new HashMap<>();
        params.put("customerId",baseModel.getCustomerId());
        params.put("categoryId",id);
        int count = commoditService.count(params);
        if(count>0){
            return R.error(1,"请先移除改分类下的商品");
        }

        commoditCategoryService.remove(id);
        return R.ok();
    }
}
