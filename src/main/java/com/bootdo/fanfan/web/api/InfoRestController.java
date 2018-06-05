package com.bootdo.fanfan.web.api;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/info")
public class InfoRestController extends ApiBaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    BootdoConfig bootdoConfig;

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

    /**
     * 获取最新html版本
     * @return
     */
    @GetMapping("/version")
    public R htmlVersion(){
        Map<Integer,String>  dicMap = dictionaryService.queryByKeys(0,DictionaryEnum.htmlVersion.getVal());

        return R.ok().put("data",dicMap.get(DictionaryEnum.htmlVersion.getVal()));
    }

    @GetMapping("/www.zip")
    public ResponseEntity<byte[]> downLoadZip(){

       byte[] fileBytes = FileUtil.readByBytes(bootdoConfig.getUploadPath()+"www.zip");

       return  new ResponseEntity<byte[]>(fileBytes, HttpStatus.OK);
    }
}
