package com.bootdo.fanfan.web.api;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.vo.APIBaseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author: JY
 * @date: 2018/5/9 15:57
 */
@Controller
public class ApiBaseRestController {

    private static ThreadLocal<APIBaseVO> local = new ThreadLocal<>();

    @ModelAttribute
    private void headerAttribute(@RequestHeader(name = "base",required = false) String base){
        if(!StringUtils.isEmpty(base)) {
            APIBaseVO baseModel = JSONObject.parseObject(base, APIBaseVO.class);
            local.set(baseModel);
        }
    }

    protected APIBaseVO getBaseModel(){
        return local.get();
    }

}
