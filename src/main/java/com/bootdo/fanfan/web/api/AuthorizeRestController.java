package com.bootdo.fanfan.web.api;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.BDException;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.AuthorizeDO;
import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.fanfan.domain.OrderAlipayDO;
import com.bootdo.fanfan.domain.enumDO.BooleanEnum;
import com.bootdo.fanfan.domain.enumDO.IdentificationEnum;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.AuthorizeService;
import com.bootdo.fanfan.vo.APIAuthorizeVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/authorize")
@Login(authority = APIAuthorityEnum.OnlyCustomer)
public class AuthorizeRestController extends ApiBaseRestController{

    @Autowired
    BootdoConfig bootdoConfig;

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    EMapper eMapper;

    @Autowired
    AlipayManager alipayManager;

    /**
     * 获取用户认证信息
     * @return
     */
    @GetMapping("/")
    public R get(){

        AuthorizeDO model = authorizeService.getByCustomerId(getBaseModel().getCustomerId());

        APIAuthorizeVO result =null;

        //创建认证数据
        if(model==null) {
            model = new AuthorizeDO();
            model.setCustomerId(getBaseModel().getCustomerId());
            // 未认证
            model.setAuthorizeState(BooleanEnum.False.getVal());
            // 未授权
            model.setIdentificationState(BooleanEnum.False.getVal());
            authorizeService.save(model);
        }

        result = eMapper.map(model, APIAuthorizeVO.class);

        //商户授权地址
        result.setAuthorizeUrl(bootdoConfig.getAuthorizeUrl()+getBaseModel().getCustomerId());

        return R.ok().put("data",result);
    }

    /**
     * 查询授权状态
     * @param id
     * @return
     */
    @GetMapping("/state")
    public R getState(Integer id){

        AuthorizeDO authorizeDO = authorizeService.get(id);

        if(authorizeDO==null){
            return R.error("无效的请求");
        }

        //未认证或者待确认时
        if(authorizeDO.getIdentificationState().equals(IdentificationEnum.NoIdentification.getVal())
                || authorizeDO.getIdentificationState().equals(IdentificationEnum.ConfirmIdentification.getVal())) {
            //todo 创建预付单 如果创建成功 则商户已经签约
            OrderAlipayDO alipayDO = new OrderAlipayDO();
            alipayDO.setSubject("验证是否签约");
            alipayDO.setTradeNo(System.currentTimeMillis() + "");
            alipayDO.setTotalAmount("0.01");
            //何总的tpId
            alipayDO.setBuyerId("2088602121036890");
            String tradeNo = alipayManager.createTradePayPlatform(alipayDO, getBaseModel().getCustomerId());
            //创建预付单成功 表示商户已经签约
            if (tradeNo != null) {
                authorizeDO.setIdentificationState(IdentificationEnum.SuccessIdentification.getVal());
                authorizeService.save(authorizeDO);
            }
        }

        APIAuthorizeVO result = eMapper.map(authorizeDO,APIAuthorizeVO.class);

        return R.ok().put("data",result);
    }

    @PostMapping("/")
    public R save(@RequestBody APIAuthorizeVO reqModel){

        AuthorizeDO model = eMapper.map(reqModel,AuthorizeDO.class);

        int success = authorizeService.save(model);

        return R.ok().put("data",success>0);
    }
}
