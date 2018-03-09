package wyx.manager.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wyx.manager.service.WyxStatisticalServiceI;

@RestController
@RequestMapping("api")
public class StatisticalController extends BaseController {

    @Autowired
    WyxStatisticalServiceI wyxStatisticalService;

    /**
     * 添加点击次数
     * @param shareid
     */
    @RequestMapping(value = "statistic/{id}")
    public void SaveStatistic(@PathVariable("id") int shareid){

        wyxStatisticalService.addClickCount(shareid);
    }

    /**
     * 更新缓存数据
     * @return
     */
    @RequestMapping(value = "statistic/execute")
    public  String GetStatistic(){
       int _updateSize = wyxStatisticalService.updateCacheData();

       return String.format("本次共更新缓存%d条数据",_updateSize);
    }

}
