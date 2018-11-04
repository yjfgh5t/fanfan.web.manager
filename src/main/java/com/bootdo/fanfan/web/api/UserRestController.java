package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.manager.AlismsManager;
import com.bootdo.fanfan.service.TpUserService;
import com.bootdo.fanfan.vo.APICustomerVO;
import com.bootdo.fanfan.vo.APICustomerRegisterVO;
import com.bootdo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    AlismsManager alismsManager;

    @Autowired
    RedisUtils redisUtils;

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
            APICustomerVO userVO = eMapper.map(userDOS.get(0), APICustomerVO.class);
            userPwd = MD5Utils.encrypt(userVO.getUsername(), userPwd);
            //判断密码输入是否正确
            if (userPwd.equals(userDOS.get(0).getPassword())) {
                //查詢店鋪Id

                return R.ok().put("data", userVO);
            }
        }

        return R.ok().put("data","");
    }

    /**
     * 商户登入-手机验证码
     * @param code
     * @return
     */
    @PostMapping("customer/codeLogin")
    public R customerCodeLogin(@Param("mobile") String mobile,@Param("code") String code){

        //手机验证码
        if(StringUtils.isEmpty(mobile+code) || !alismsManager.checkCode(mobile,code)){
            return R.error("验证码输入不正确");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);

        List<com.bootdo.system.domain.UserDO> userDOS =userService.list(params);

        if(userDOS!=null && userDOS.size()>0) {
            APICustomerVO userVO = eMapper.map(userDOS.get(0), APICustomerVO.class);
            return R.ok().put("data", userVO);
        }else{
            return R.error("手机号还未注册");
        }
    }

    /**
     * 保存商户信息
     * @param model
     * @param result
     * @return
     */
    @PostMapping("customer/save")
    public R customerSave(@Validated @RequestBody APICustomerVO model, BindingResult result){

        //验证Model
        checkError(result);

        com.bootdo.system.domain.UserDO userDO = eMapper.map(model, com.bootdo.system.domain.UserDO.class);
        int success=0;
        if(model.getUserId()!=0){

            com.bootdo.system.domain.UserDO oldModel = userService.get(model.getUserId());

            if(StringUtils.isNotEmpty(userDO.getPassword())){
                userDO.setPassword(MD5Utils.encrypt(oldModel.getUsername(),userDO.getPassword()));
            }
            success = userService.simpleSave(userDO);
        }

        if(success>0) {
            return R.ok().put("data", model);
        }else {
            return R.error(1,"保存失败");
        }
    }

    /**
     * 商户信息注册
     * @param registerVO
     * @param result
     * @return
     */
    @PostMapping("customer/register")
    public R customerRegister(@Validated @RequestBody APICustomerRegisterVO registerVO, BindingResult result){

        //验证Model
        checkError(result);

        //手机验证码
        if(!alismsManager.checkCode(registerVO.getMobile(),registerVO.getCode())){
            return R.error("验证码输入不正确");
        }

        com.bootdo.system.domain.UserDO userDO = eMapper.map(registerVO, com.bootdo.system.domain.UserDO.class);

        if(userService.simpleSave(userDO)>0){

            //如果是支付宝小程序注册成功 向商户发送下载Apk
            if(getBaseModel().getClientEnumType() == PlatformEnum.AlipayMiniprogram){
                alismsManager.sendSMSDownloadApk(userDO.getMobile());
            }

            return R.ok().put("data",userDO);
        }

        return R.error("注册失败请重试！");
    }

    /**
     * 发送二维码
     * @param mobile
     * @return
     */
    @PostMapping("/sendCode")
    public R sendCode(String mobile, String imgCode, HttpServletRequest request){

        if(StringUtils.isEmpty(mobile) || mobile.length()!=11){
            return R.error("输入的手机号不正确");
        }

        String ipAddress = getClientIp(request);

        Boolean hasSend = (Boolean) redisUtils.hget(RedisConstant.SMS_SEND_RECORD,ipAddress);

        //如果发送一次 则验证imgCode
        if(hasSend!=null){

            if(StringUtils.isEmpty(imgCode)){
                return R.error("图片验证码输入不正确");
            }

            String cacheImgCode = (String) redisUtils.hget(RedisConstant.IMG_CODE_KEY,mobile);

            if(StringUtils.isEmpty(cacheImgCode) || !imgCode.toLowerCase().equals(cacheImgCode.toLowerCase())){
                return R.error("图片验证码输入不正确");
            }
        }

        if(alismsManager.sendSMSCode(mobile)){
            //记录已发送 30分钟
            redisUtils.hset(RedisConstant.SMS_SEND_RECORD,ipAddress,true,60*30);
            return R.ok();
        }

        return R.error("验证码发送失败");
    }

    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
