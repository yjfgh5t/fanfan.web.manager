package com.bootdo.fanfan.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.constant.SessionConstant;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.vo.APIBaseVO;
import com.bootdo.fanfan.vo.APICustomerVO;
import com.bootdo.fanfan.vo.APIUserVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class APILoginInterceptor implements HandlerInterceptor {

    private RedisUtils redisUtils;

    public APILoginInterceptor(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        String base = httpServletRequest.getHeader("base");

        if (StringUtils.isEmpty(base)) {
            httpServletResponse.setStatus(400);
            return false;
        }

        //验证
        APIBaseVO baseModel = JSONObject.parseObject(base, APIBaseVO.class);
        if (baseModel == null) {
            httpServletResponse.setStatus(400);
            return false;
        }

        HandlerMethod method = (HandlerMethod) object;
        int code = checkLogin(method, httpServletRequest, baseModel);
        if (code > 0) {
            httpServletResponse.setStatus(code);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 验证商户是否需要登录
     * @param method
     * @param httpServletRequest
     * @return
     */
    private int checkLogin(HandlerMethod method,HttpServletRequest httpServletRequest,APIBaseVO baseModel) {
        //判断是否需要登录
        Login login = AnnotationUtils.findAnnotation(method.getMethod(), Login.class);
        if (Objects.isNull(login)) {
            login = AnnotationUtils.findAnnotation(method.getBeanType(), Login.class);
            if (Objects.isNull(login)) {
                return 0;
            }
        }


        Object loginInfo = null;
        //验证用户是否登录
        String sessionKey = httpServletRequest.getHeader("x-auth-token");
        //登录信息
        if (StringUtils.isNotEmpty(sessionKey)) {
            loginInfo = redisUtils.get(RedisConstant.FANFAN_USER_LOGIN + sessionKey);
        }

        //需要验证登录
        if (login.require() && loginInfo == null) {
            return 401;
        }

        //验证权限
        if(login.authority()!=APIAuthorityEnum.All){
            //权限匹配
            if(login.authority()==APIAuthorityEnum.OnlyCustomer && baseModel.getClientEnumType()!=PlatformEnum.CustomerAndroid){
                return 402;
            }

            //权限匹配
            if(login.authority()==APIAuthorityEnum.OnlyUser && baseModel.getClientEnumType()==PlatformEnum.CustomerAndroid){
                return 402;
            }
        }

        //无需验证登录
        if(!login.require()){
            return 0;
        }

        //已登录 判断是否有权限
        if(loginInfo!=null) {
            if (login.authority() == APIAuthorityEnum.OnlyCustomer) {
                //验证必须登录
                if (loginInfo instanceof APICustomerVO) {
                    APICustomerVO customerVO = (APICustomerVO) loginInfo;
                    if (customerVO.getUserId().equals(baseModel.getCustomerId())) {
                        return 0;
                    }
                }
                return 402;
            }

            if (login.authority() == APIAuthorityEnum.OnlyUser) {
                if (loginInfo instanceof APIUserVO) {
                    APIUserVO userVO = (APIUserVO) loginInfo;
                    if (userVO.getUserId().equals(baseModel.getUserId())) {
                        return 0;
                    }
                }
                return 402;
            }
        }
        return 0;
    }
}
