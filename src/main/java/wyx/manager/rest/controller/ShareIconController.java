package wyx.manager.rest.controller;

import org.dozer.Mapper;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wyx.manager.entity.WyxShareIconEntity;
import wyx.manager.extend.EMapper;
import wyx.manager.service.WyxShareIconServiceI;
import wyx.manager.vo.ShareIconVO;

import java.util.List;

@RestController
@RequestMapping("api")
public class ShareIconController extends BaseController{

    @Autowired
    WyxShareIconServiceI wyxShareIconService;

    @Autowired
    EMapper mapper;

    /**
     * 查询所有icon
     * @return
     */
    @RequestMapping(value = "shareicon",method = RequestMethod.GET)
    public List<ShareIconVO> GetArray(){

        //查询条件
        CriteriaQuery criteriaQuery = new CriteriaQuery(WyxShareIconEntity.class);
        criteriaQuery.eq("siDelete","1");

        //获取查询集合
       List<WyxShareIconEntity> shareIconList = wyxShareIconService.getListByCriteriaQuery(criteriaQuery,false);

       return  mapper.mapArray(shareIconList,ShareIconVO.class);
    }

}
