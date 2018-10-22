package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.vo.APIBaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: JY
 * @date: 2018/5/9 15:57
 */
@Controller
public class ApiBaseRestController {

    private static ThreadLocal<APIBaseVO> local = new ThreadLocal<>();

    @ModelAttribute
    private void headerAttribute(@RequestHeader(name = "base",required = false) String base, HttpServletResponse response) throws IOException {
        if(!StringUtils.isEmpty(base)) {
            APIBaseVO baseModel = JSONObject.parseObject(base, APIBaseVO.class);
//            if(!checkSign(baseModel)){
//                response.sendError(500,"签名失败");
//                return;
//            }
            local.set(baseModel);
        }
    }

    protected APIBaseVO getBaseModel(){
        return local.get();
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
        if(baseVO.getClientType()== PlatformEnum.AlipayMiniprogram.toString()) {
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
}
