package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.ContactDO;
import com.bootdo.fanfan.service.ContactService;
import com.bootdo.fanfan.vo.APIConcatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: JY
 * @date: 2018/10/22 11:29
 */
@RestController
@RequestMapping(value = "/api/concat")
public class ConcatRestController extends ApiBaseRestController {

    @Autowired
    EMapper eMapper;

    @Autowired
    ContactService contactService;

    /**
     * 保存收货地址
     * @param
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIConcatVO concatVO){
        ContactDO contactDO  = eMapper.map(concatVO,ContactDO.class);
        contactService.save(contactDO);
        return R.ok();
    }

}
