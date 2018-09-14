package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.vo.APIShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: JY
 * @date: 2018/9/12 17:16
 */
@RestController
@RequestMapping("/api/shop")
public class ShopRestController extends ApiBaseRestController{

    @Autowired
    ShopService shopService;
    @Autowired
    EMapper eMapper;

    @GetMapping("/{id}")
    public R get(@PathVariable("id") Integer id){

        ShopDO shopDO =null;
        if(id>0) {
           shopDO = shopService.get(id);
        }else {
            shopDO = shopService.getByCustomerId(getBaseModel().getCustomerId());
        }

        APIShopVO shopVO=null;

        if(shopDO!=null){
            shopVO = eMapper.map(shopDO, APIShopVO.class);
        }

        return R.ok().put("data",shopVO);
    }

    @PostMapping("/")
    public R save(@RequestBody APIShopVO shopVO){

        shopVO.setCustomerId(getBaseModel().getCustomerId());

        ShopDO shopDO = eMapper.map(shopVO,ShopDO.class);

        int success=0;

        if(shopVO.getId()==-1){
            success = shopService.save(shopDO);
        } else {
            success = shopService.update(shopDO);
        }

        if(success>0) {
            return R.ok().put("data",shopDO.getId());
        }else {
            return R.error(1,"保存失败");
        }
    }

}
