package com.bootdo.fanfan.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.manager.GDMapManager;
import com.bootdo.fanfan.vo.APIAddressVO;
import com.bootdo.fanfan.vo.APIGDMapTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/address")
public class AddressRestController {

    @Autowired
    GDMapManager gdMapManager;

    @Autowired
    EMapper  eMapper;

    /**
     * 查询地理位置
     * @param keyWord
     * @return
     */
    @GetMapping("/search")
    public R querAddress(String keyWord,String lat,String log){

        //地址列表
        List<APIGDMapTip> list = gdMapManager.queryAddr(keyWord,lat,log);

        return R.ok().put("data",eMapper.mapArray(list,APIAddressVO.class));
    }

}
