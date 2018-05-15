package com.bootdo.fanfan.api;

import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.DictionaryDO;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoRestController extends ApiBaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 初始化信息
     * @return
     */
    @GetMapping("/")
    public R initInfo(){

        //字典信息
        Map<Integer,String> dictionaryDOList = dictionaryService.queryByKeys(baseModel.getCustomerId(),
                DictionaryEnum.businessEndTime.getVal(),
                DictionaryEnum.businessStartTime.getVal(),
                DictionaryEnum.minOrderPrice.getVal(),
                DictionaryEnum.shopName.getVal()
        );

        //返回的结果集
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("dict",dictionaryDOList);

        return R.ok().put("data",resultData);
    }

}
