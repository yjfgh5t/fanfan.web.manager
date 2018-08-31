package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.service.DeskService;
import com.bootdo.fanfan.service.QrcodeService;
import com.bootdo.fanfan.vo.APIDeskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: JY
 * @date: 2018/7/3 12:22
 */
@RestController
@RequestMapping(value = "/api/desk")
public class DeskRestController extends ApiBaseRestController {

    @Autowired
    DeskService deskService;
    @Autowired
    QrcodeService qrcodeService;

    @Autowired
    private EMapper mapper;

    /**
     * 查询客桌列表
     * @return
     */
    @GetMapping("/")
    public R list(){
        List<APIDeskVO> deskList =  mapper.mapArray(deskService.queryList(getBaseModel().getCustomerId()), APIDeskVO.class);
        return R.ok().put("data", deskList);
    }

    /**
     * 保存客桌
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIDeskVO deskVO){
        DeskDO deskDO = mapper.map(deskVO,DeskDO.class);
        if(deskVO.getId()==-1){
            deskDO.setCustomerId(getBaseModel().getCustomerId());
            deskService.save(deskDO);
        }else {
            deskService.update(deskDO);
            //修改二维码
            if(StringUtils.isNotEmpty(deskVO.getQrCodeId())){
                QrcodeDO qrcode = new QrcodeDO();
                qrcode.setId(deskVO.getQrCodeId());
                qrcode.setCustomerId(getBaseModel().getCustomerId());
                qrcode.setDeskId(deskVO.getId());
                //去除原有的标记
                qrcodeService.removeOldDesk(qrcode.getCustomerId(),qrcode.getDeskId());
                //设置新的标记
                qrcodeService.update(qrcode);
            }
        }
        return R.ok();
    }

    /**
     * 删除客桌
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){
        deskService.remove(id);
        return R.ok();
    }

    /**
     * 获取客桌二维码
     * @param id
     * @return
     */
    @GetMapping("/qrcode/{id}")
    public R deskQRCode(@PathVariable("id") Integer id){
        return R.ok().put("data",deskService.getQRCode(id));
    }

}
