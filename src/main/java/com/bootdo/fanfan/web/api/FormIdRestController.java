package com.bootdo.fanfan.web.api;

import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.FormIdDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.service.FormIdService;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JY
 * @date: 2018/8/10 17:04
 */
@RestController
@RequestMapping("/api/formId")
public class FormIdRestController extends ApiBaseRestController {

    @Autowired
    FormIdService formIdService;

    /**
     * 添加formId
     * @param formId
     * @return
     */
    @PostMapping("/")
    public R add(String formId,String tpId){

        if(getBaseModel().getUserId()==null || getBaseModel().getUserId()<0){
            return R.error("用户未登录");
        }

        FormIdDO formIdDO = new FormIdDO();
        formIdDO.setFormId(formId);
        formIdDO.setUserId(getBaseModel().getUserId());
        formIdDO.setTpId(tpId);

        //判断客户端类型
        if(PlatformEnum.AlipayMiniprogram==getBaseModel().getClientEnumType()){
            formIdDO.setFormType(PlatformEnum.AlipayMiniprogram.getVal());
            //支付宝每个formid可以使用3次
            formIdDO.setUseCount(3);
        }else if(PlatformEnum.WechatMiniprogram == getBaseModel().getClientEnumType()){
            formIdDO.setFormType(PlatformEnum.WechatMiniprogram.getVal());
            //支付宝每个formid可以使用1次
            formIdDO.setUseCount(1);
        }

        //执行保存
        formIdService.save(formIdDO);

        return R.ok().put("data",true);
    }
}
