package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.service.TpUserService;
import com.bootdo.fanfan.vo.APIBusinessVO;
import com.bootdo.system.service.UserService;
import com.bootdo.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
     * 商户登入
     * @param userPwd
     * @return
     */
    @PostMapping("customer/login")
    public R customerLogin(@Param("mobile") String mobile,@Param("userPwd") String userPwd){

        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);

        List<com.bootdo.system.domain.UserDO> userDOS =userService.list(params);

        if(userDOS!=null && userDOS.size()>0) {
            APIBusinessVO userVO = eMapper.map(userDOS.get(0), APIBusinessVO.class);
            userPwd = MD5Utils.encrypt(userVO.getUsername(), userPwd);
            //判断密码输入是否正确
            if (userPwd.equals(userDOS.get(0).getPassword())) {
                //查詢店鋪Id

                return R.ok().put("data", userVO);
            }
        }

        return R.ok().put("data","");
    }
}
