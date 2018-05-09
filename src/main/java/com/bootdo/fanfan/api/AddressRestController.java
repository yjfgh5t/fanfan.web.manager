package com.bootdo.fanfan.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.ReceiverDO;
import com.bootdo.fanfan.manager.GDMapManager;
import com.bootdo.fanfan.service.ReceiverService;
import com.bootdo.fanfan.vo.APIAddressVO;
import com.bootdo.fanfan.vo.APIGDMapTip;
import com.bootdo.fanfan.vo.APIReceiverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/address")
public class AddressRestController extends ApiBaseRestController {

    @Autowired
    GDMapManager gdMapManager;

    @Autowired
    EMapper  eMapper;

    @Autowired
    ReceiverService  receiverService;

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

    /**
     * 保存收货地址
     * @param receiverModel
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIReceiverVO receiverModel){

        ReceiverDO receiverDO  = eMapper.map(receiverModel,ReceiverDO.class);

        receiverService.save(receiverDO);

        return R.ok();
    }

    @GetMapping("/")
    public R getList(){

        Map<String,Object> params =  new HashMap<>();

        params.put("userId",baseModel.getUserId());

        List<ReceiverDO> list = receiverService.list(params);

        List<APIReceiverVO> renList=new ArrayList<>();

        if(list!=null &&  list.size()>0)
            renList = eMapper.mapArray(list,APIReceiverVO.class);

        return R.ok().put("data",renList);
    }

}
