package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.service.QrcodeService;
import com.bootdo.fanfan.vo.APIDeskVO;
import com.bootdo.fanfan.vo.APIQRCodeVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/12/20 15:45
 */
@RestController
@RequestMapping(value = "/api/qrcode")
@Login(authority = APIAuthorityEnum.OnlyCustomer)
public class QRCodeRestController extends ApiBaseRestController {

    @Autowired
    QrcodeService qrcodeService;

    @Autowired
    private EMapper mapper;

    /**
     * 查询二维码列表
     * @return
     */
    @GetMapping("/list")
    public R list(){
        Map<String,Object> params = new HashMap<>();
        params.put("customerId",getBaseModel().getCustomerId());
        List<QrcodeDO> list = qrcodeService.list(params);
        List<APIQRCodeVO> deskList =  mapper.mapArray(list, APIQRCodeVO.class);
        return R.ok().put("data", deskList);
    }

    /**
     * 保存二维码
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody APIQRCodeVO model){
        QrcodeDO qrcodeDO = mapper.map(model,QrcodeDO.class);
        if(StringUtils.isEmpty(qrcodeDO.getId())){
            qrcodeDO.setCustomerId(getBaseModel().getCustomerId());
            qrcodeService.save(qrcodeDO);
        }else {
            qrcodeService.update(qrcodeDO);
        }
        return R.ok();
    }


}
