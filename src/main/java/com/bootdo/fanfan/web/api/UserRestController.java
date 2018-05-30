package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.service.TpUserService;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/user")
public class UserRestController extends ApiBaseRestController {

    @Autowired
    TpUserService tpUserService;

    @Autowired
    UserService  userService;

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


    /**
     * 登入
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("/login")
    public R login(@Param("userName") String userName,@Param("userPwd") String userPwd){

        userPwd = MD5Utils.encrypt(userName, userPwd);

        Map<String,Object> params = new HashMap<>();
        params.put("username",userName);
        params.put("password",userPwd);

       boolean hasUser =userService.list(params).size()>0;

       return R.ok().put("data",hasUser);
    }
}
