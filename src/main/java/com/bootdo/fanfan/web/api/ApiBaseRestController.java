package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.exception.BDException;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.RedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.vo.APIBaseVO;
import com.bootdo.fanfan.vo.APICustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author: JY
 * @date: 2018/5/9 15:57
 */
@Controller
public class ApiBaseRestController {

    private static ThreadLocal<APIBaseVO> local = new ThreadLocal<>();

    @Autowired
    RedisUtils redisUtils;

    @ModelAttribute
    private void headerAttribute(@RequestHeader(name = "base",required = false) String base, HttpServletResponse response) throws IOException {
        if(!StringUtils.isEmpty(base)) {
            APIBaseVO baseModel = JSONObject.parseObject(base, APIBaseVO.class);
            local.set(baseModel);
        }
    }

    protected APIBaseVO getBaseModel(){
        return local.get();
    }

    /**
     * 保存登录信息
     * @param userModel
     */
    protected String saveLogin(Object userModel) {
        String sessionKey = UUID.randomUUID().toString().toLowerCase();
        redisUtils.set(RedisConstant.FANFAN_USER_LOGIN + sessionKey, userModel, 7200);
        return sessionKey;
    }

    /**
     * 验证签名
     * @return
     */
    private boolean checkSign(APIBaseVO baseVO){



        if(StringUtils.isEmpty(baseVO.getVersion())){
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        //支付宝小程序
        if(baseVO.getClientEnumType()== PlatformEnum.AlipayMiniprogram) {
            stringBuilder.append(baseVO.getClientType());
            stringBuilder.append(baseVO.getUserId());
            stringBuilder.append(baseVO.getCustomerId());
            stringBuilder.append(baseVO.getVersion());
            stringBuilder.append(baseVO.getTime());
            stringBuilder.append("miniprogram");
        }

        String strSign = MD5Utils.simpleEncrypt(stringBuilder.toString());

        return strSign.equals(baseVO.getSign());
    }

    /**
     * 验证
     * @param result
     * @return
     */
    protected void checkError(BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            if(allErrors!=null && allErrors.size()>0){
                throw new BDException(allErrors.get(0).getDefaultMessage(),BDException.VERIFY_ERROR_CODE);
            }
        }
    }
}
