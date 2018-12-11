package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.AuthorizeDO;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.IdentificationEnum;
import com.bootdo.fanfan.service.AuthorizeService;
import com.bootdo.fanfan.service.CommodityService;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.vo.APIShopVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/9/12 17:16
 */
@RestController
@RequestMapping("/api/shop")
@Login(authority = APIAuthorityEnum.OnlyCustomer)
public class ShopRestController extends ApiBaseRestController{

    @Autowired
    ShopService shopService;

    @Autowired
    CommodityService commodityService;

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    EMapper eMapper;

    @GetMapping("/{id}")
    @Login(authority = APIAuthorityEnum.All)
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
        ShopDO shopDO = eMapper.map(shopVO,ShopDO.class);
        int success=0;
        if(shopVO.getId()==-1){
            shopDO.setCustomerId(getBaseModel().getCustomerId());
            success = shopService.save(shopDO);
        } else {
            success = shopService.update(shopDO);
        }
        if(success>0) {
            return R.ok().put("data",shopDO.getId());
        }else {
            return R.error("保存失败");
        }
    }

    @PostMapping("/payType")
    @Login(authority = APIAuthorityEnum.All)
    public R payType(Integer shopId){
        APIShopVO shopVO =null;

        if(shopId!=null){
            ShopDO shopDO = shopService.getPayType(shopId);
            shopVO = eMapper.map(shopDO,APIShopVO.class);
        }
        return R.ok().put("data",shopVO);
    }

    /**
     * 店铺收款设置
     * @param shopVO
     * @return
     */
    @PostMapping("/shopPaySetting")
    public R shopPaySetting(@RequestBody APIShopVO shopVO){

        //都设置为否
        if(!shopVO.getAlipay() && !shopVO.getOffline() &&  !shopVO.getWechat()){
            return R.error("至少开通一个收款方式");
        }
        //设置支付宝收款
        if(shopVO.getAlipay()){
            //查询是否已经签约
            AuthorizeDO authorizeDO = authorizeService.getByCustomerId(getBaseModel().getCustomerId());
            if(authorizeDO==null || BooleanEnum.False.getVal().equals(authorizeDO.getAuthorizeState())){
                return R.ok("店铺还未授权").put("data",-1);
            }
            //未认证
            if(!IdentificationEnum.SuccessIdentification.getVal().equals(authorizeDO.getIdentificationState())){
                return R.ok("店铺还未认证").put("data",-1);
            }
        }
        return save(shopVO);
    }
    /**
     * 店铺状态  1：店铺未设置，2：商品未设置 3：支付宝未授权 4：支付宝未认证 5:支付宝认证失败 6:支付宝认证待确认 9:信息已完善
     * @return
     */
    @PostMapping("/state")
    public R state(Integer state) {
        //查询店铺状态
        if (state <= 1) {
            ShopDO shopDO = shopService.getByCustomerId(getBaseModel().getCustomerId());
            if (shopDO == null || StringUtils.isEmpty(shopDO.getName())) {
                return R.ok().put("data", 1);
            }
        }

        if (state <= 2) {
            //商品设置状态
            Map<String, Object> params = new HashMap<>();
            params.put("customerId", getBaseModel().getCustomerId());
            params.put("delete", 0);
            int count = commodityService.count(params);
            if (count == 0) {
                return R.ok().put("data", 2);
            }
        }

        if (state <= 6) {
            //授权状态
            AuthorizeDO authorizeDO = authorizeService.getByCustomerId(getBaseModel().getCustomerId());
            //未授权
            if (authorizeDO == null || BooleanEnum.False.getVal().equals(authorizeDO.getAuthorizeState())) {
                return R.ok().put("data", 3);
            }
            //未认证
            if (IdentificationEnum.NoIdentification.getVal().equals(authorizeDO.getIdentificationState())) {
                return R.ok().put("data", 4);
            }
            //认证失败
            if (IdentificationEnum.FailIdentification.getVal().equals(authorizeDO.getIdentificationState())) {
                return R.ok().put("data", 5);
            }
            //认证确认
            if (IdentificationEnum.ConfirmIdentification.getVal().equals(authorizeDO.getIdentificationState())) {
                return R.ok().put("data", 6);
            }
        }

        return R.ok().put("data", 9);
    }
}
