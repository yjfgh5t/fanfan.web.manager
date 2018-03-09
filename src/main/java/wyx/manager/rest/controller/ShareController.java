package wyx.manager.rest.controller;

import org.jeecgframework.core.annotation.JAuth;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wyx.manager.entity.WyxShareEntity;
import wyx.manager.entity.WyxStatisticalEntity;
import wyx.manager.entity.WyxTempPacketEntity;
import wyx.manager.extend.EMapper;
import wyx.manager.service.WyxShareServiceI;
import wyx.manager.service.WyxStatisticalServiceI;
import wyx.manager.service.WyxTempPacketServiceI;
import wyx.manager.vo.ShareStatisticVO;
import wyx.manager.vo.ShareVO;

import java.util.List;
import java.util.Map;

@JAuth(auth = Permission.SKIP_AUTH)
@RestController
@RequestMapping("api/shares")
public class ShareController extends BaseController{

    @Autowired
    WyxShareServiceI wyxShareService;

    @Autowired
    WyxTempPacketServiceI wyxTempPacketService;

    @Autowired
    WyxStatisticalServiceI wyxStatisticalService;

    @Autowired
    EMapper mapper;


    /**
     * 添加分享
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Integer SaveShare(@RequestBody ShareVO model) throws Exception {
        if(model==null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return 0;
        }
        //数据转换
        WyxShareEntity shareModel = mapper.map(model,WyxShareEntity.class);

        WyxTempPacketEntity tempPacket = mapper.map(model,WyxTempPacketEntity.class);

        //分享数据
        return wyxShareService.save(shareModel,tempPacket);
    }

    /**
     * 获取分享口令-单个
     * @param shareid
     * @return
     */
    @RequestMapping(value = "{id}")
    public String GetShare(@PathVariable("id") int shareid)
    {
        //获取模板数据
        List<WyxTempPacketEntity> listArray = wyxTempPacketService.findByProperty(WyxTempPacketEntity.class,"id",shareid);

        if(null==listArray || listArray.size()==0) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        //增加查看次数
        wyxStatisticalService.addSeeCount(shareid);

        return listArray.get(0).getTpToken();
    }

    /**
     * 获取分享口令-列表
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Map<String,Object>> GetShareArray(@RequestParam("uid") String createid){

        String _Sql =String.format("select tbl.id ,tbl.s_title as stitle,tbl.s_describe as sdescribe,sum(tbl.s_click) as sclicknum,sum(tbl.s_see) as sseenum from ( \n" +
                "select tbs.id,tbs.s_title,tbs.s_describe,tbss.s_see,tbss.s_click from wyx_share as tbs INNER JOIN wyx_statistical tbss\n" +
                "on tbs.id = tbss.sid \n" +
                "where tbs.t_delete=1 and tbs.create_id='%s' ORDER BY tbs.update_date DESC\n" +
                ") as tbl \n" +
                "GROUP BY tbl.id,tbl.s_title,tbl.s_describe",createid);

        List<Map<String,Object>> _listData = wyxShareService.findForJdbc(_Sql);
        if(null==_listData || _listData.size()==0)
        {
            return null;
        }

        return _listData;
    }

}
