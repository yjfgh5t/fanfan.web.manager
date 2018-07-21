package com.bootdo.fanfan.web.api;

import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.service.DeskService;
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
    private EMapper mapper;

    /**
     * 查询商品列表
     * @return
     */
    @GetMapping("/")
    public R list(){
        Map<String,Object> parmas = new HashMap<>();
        parmas.put("sort","`id`");
        parmas.put("order","asc");
        parmas.put("customerId",this.baseModel.getCustomerId());
        List<APIDeskVO> deskList =  mapper.mapArray(deskService.list(parmas), APIDeskVO.class);
        return R.ok().put("data", deskList);
    }

    /**
     * 保存商品
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIDeskVO deskVO){
        DeskDO deskDO = mapper.map(deskVO,DeskDO.class);
        if(deskVO.getId()==-1){
            deskDO.setCustomerId(this.baseModel.getCustomerId());
            deskService.save(deskDO);
        }else {
            deskService.update(deskDO);
        }
        return R.ok();
    }

    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id){
        deskService.remove(id);
        return R.ok();
    }

}
