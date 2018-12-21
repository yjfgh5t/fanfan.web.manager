package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.DeliveryDO;
import com.bootdo.fanfan.service.DeliveryService;
import com.bootdo.fanfan.vo.APIDeliveryVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: JY
 * @date: 2018/12/14 14:52
 */
@RestController
@RequestMapping(value = "/api/delivery")
@Login(authority = APIAuthorityEnum.OnlyCustomer)
public class DeliveryRestController extends ApiBaseRestController {

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    EMapper eMapper;

    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public R getList(){

        List<DeliveryDO> deliveryArrays = deliveryService.getByCustomerId(getBaseModel().getCustomerId());

        List<APIDeliveryVO> resultArrays = eMapper.mapArray(deliveryArrays, APIDeliveryVO.class);

        return R.ok().put("data",resultArrays);
    }

    /**
     * 保存
     * @param req
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody APIDeliveryVO req){

        DeliveryDO model = eMapper.map(req,DeliveryDO.class);
        model.setCustomerId(getBaseModel().getCustomerId());

        if(model.getId()<0){
            deliveryService.save(model);
        }else{
            deliveryService.update(model);
        }
        return R.ok().put("data",true);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer id){

        deliveryService.remove(id);

        return R.ok().put("data",true);
    }

    /**
     * 设置默认
     * @param id
     * @return
     */
    @PostMapping("/default")
    public R setDefault(Integer id) {

        deliveryService.setDefault(id);

        return R.ok().put("data", true);
    }

}
