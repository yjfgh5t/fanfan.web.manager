package com.bootdo.fanfan.api;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.TpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {

    @Autowired
    TpUserService tpUserService;

    @Autowired
    EMapper eMapper;

    @PostMapping("/")
    public R getUser(@Param("code") String code,@Param("type") Integer type){

        TpUserDO tpUserDO = tpUserService.getTPInfo(code,type);

        if(tpUserDO!=null) {
           return R.ok().put("data", eMapper.map(tpUserDO, UserDO.class));
        }
        return  R.error(1,"获取用户新失败");
    }


}
