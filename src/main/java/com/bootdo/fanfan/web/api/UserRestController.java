package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.constant.SessionConstant;
import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.manager.AlismsManager;
import com.bootdo.fanfan.manager.WechatManager;
import com.bootdo.fanfan.manager.model.wechat.WXJSCodeModel;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.service.TpUserService;
import com.bootdo.fanfan.vo.APICustomerVO;
import com.bootdo.fanfan.vo.APICustomerRegisterVO;
import com.bootdo.fanfan.vo.APIUserVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.vo.request.APIUserReq;
import com.bootdo.fanfan.web.interceptor.Login;
import com.bootdo.system.service.UserService;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
    WechatManager wechatManager;

    @Autowired
    ShopService shopService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    EMapper eMapper;

    /**
     * 支付宝设置用信息
     * @param code
     * @param type
     * @return
     */
    @PostMapping("/")
    public R getUser(@Param("code") String code,@Param("type") Integer type){

        TpUserDO tpUserDO = tpUserService.setAlipayTPInfo(code,PlatformEnum.get(type),getBaseModel().getCustomerId());

        if(tpUserDO!=null) {
            APIUserVO userModel = eMapper.map(tpUserDO, APIUserVO.class);
            //保存登录信息
            saveLogin(userModel);
           return R.ok().put("data", userModel);
        }
        return  R.error(1,"获取用户信息失败");
    }

    /**
     * 微信设置用户信息
     * @param userReq
     * @return
     */
    @PostMapping("/wxUser")
    public R getWXUser(@RequestBody APIUserReq userReq){
        WXJSCodeModel codeModel = wechatManager.getJsCodeModel(userReq.getCode());
        if(codeModel!=null){
            userReq.setTpAppId(codeModel.getAppId());
            userReq.setTpId(codeModel.getOpenid());
            userReq.setTpType(PlatformEnum.WechatMiniprogram.getVal());
            TpUserDO tpUserDO = eMapper.map(userReq,TpUserDO.class);
            tpUserDO = tpUserService.getTPInfo(tpUserDO,PlatformEnum.WechatMiniprogram,getBaseModel().getCustomerId());
            if(tpUserDO!=null) {
                APIUserVO userModel = eMapper.map(tpUserDO, APIUserVO.class);
                //保存登录信息
                saveLogin(userModel);
                return R.ok().put("data", userModel);
            }
        }

        return R.error(1,"获取用户信息失败");
    }


    /**
     * 商户登入
     * @param userPwd
     * @return
     */
    @PostMapping("customer/login")
    @Login(require = false,authority = APIAuthorityEnum.OnlyCustomer)
    public R customerLogin(@Param("mobile") String mobile, @Param("userPwd") String userPwd){
        Map<String,Object> params = new HashMap<>();
        params.put("mobile",mobile);
        List<com.bootdo.system.domain.UserDO> userDOS =userService.list(params);
        if(userDOS!=null && userDOS.size()>0) {
            APICustomerVO userVO = eMapper.map(userDOS.get(0), APICustomerVO.class);
            userPwd = MD5Utils.encrypt(userVO.getUsername(), userPwd);
            //判断密码输入是否正确
            if (userPwd.equals(userDOS.get(0).getPassword())) {
                Map<String, Object> result = customerLogin(userVO);
                return R.ok().put("data", result);
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
    @Login(require = false,authority = APIAuthorityEnum.OnlyCustomer)
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
            Map<String, Object> result = customerLogin(userVO);
            return R.ok().put("data", result);
        }else{
            return R.error("手机号还未注册");
        }
    }

    @PostMapping("customer/autoLogin")
    @Login(require = false,authority = APIAuthorityEnum.OnlyCustomer)
    public R customerAutoLogin(@Param("userId") Long userId,@Param("pwd") String pwd){

        if(userId==null || !StringUtils.isNotEmpty(pwd)){
            return R.error("自动登录失败");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("password",pwd);

        List<com.bootdo.system.domain.UserDO> userDOS =userService.list(params);
        if(userDOS!=null && userDOS.size()>0) {
            APICustomerVO userVO = eMapper.map(userDOS.get(0), APICustomerVO.class);
            Map<String, Object> result = customerLogin(userVO);
            return R.ok().put("data", result);
        }else{
            return R.error("自动登录失败");
        }
    }

    private Map<String,Object> customerLogin( APICustomerVO userVO) {
        //查询店铺Id
        String shopName = shopService.getNameByCustomerId(userVO.getUserId().intValue());
        userVO.setShopName(shopName);
        //将用户信息存入Redis
        String tokenKey = saveLogin(userVO);

        Map<String, Object> resultParams = new HashMap<>();
        resultParams.put("userInfo", userVO);
        resultParams.put("token", tokenKey);
        return resultParams;
    }

    /**
     * 保存商户信息
     * @param model
     * @param result
     * @return
     */
    @PostMapping("customer/save")
    @Login(authority = APIAuthorityEnum.OnlyCustomer)
    public R customerSave(@Validated @RequestBody APICustomerVO model, BindingResult result){

        //验证Model
        checkError(result);

        com.bootdo.system.domain.UserDO userDO = eMapper.map(model, com.bootdo.system.domain.UserDO.class);
        int success=0;
        if(model.getUserId()!=0){

            com.bootdo.system.domain.UserDO oldModel = userService.get(model.getUserId().longValue());

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
                return R.error(1,"图形验证码输入不正确");
            }
            String cacheImgCode = (String) redisUtils.hget(RedisConstant.IMG_CODE_KEY,mobile);

            if(StringUtils.isEmpty(cacheImgCode) || !imgCode.toLowerCase().equals(cacheImgCode.toLowerCase())){
                return R.error(2,"图形验证码输入不正确");
            }
        }

        if(alismsManager.sendSMSCode(mobile)){
            //记录已发送 30分钟
            redisUtils.hset(RedisConstant.SMS_SEND_RECORD,ipAddress,true,60*30);
            return R.ok();
        }

        return R.error(3,"验证码发送失败");
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
