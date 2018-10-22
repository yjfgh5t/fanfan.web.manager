package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.ContactDO;
import com.bootdo.fanfan.domain.ReceiverDO;
import com.bootdo.fanfan.service.ContactService;
import com.bootdo.fanfan.vo.APIConcatVO;
import com.bootdo.fanfan.vo.APIReceiverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param concatVO
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIConcatVO concatVO){

        ContactDO contactDO  = eMapper.map(concatVO,ContactDO.class);

        contactService.save(contactDO);

        return R.ok();
    }

}
