package com.bootdo.fanfan.web.api;

import com.alibaba.druid.util.StringUtils;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.utils.AMapUtils;
import com.bootdo.common.utils.R;
import com.bootdo.fanfan.domain.ReceiverDO;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.domain.enumDO.PlatformEnum;
import com.bootdo.fanfan.manager.GDMapManager;
import com.bootdo.fanfan.service.ReceiverService;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.vo.APIAddressVO;
import com.bootdo.fanfan.vo.APIGDMapTipVO;
import com.bootdo.fanfan.vo.APIReceiverVO;
import com.bootdo.fanfan.vo.enums.APIAuthorityEnum;
import com.bootdo.fanfan.web.interceptor.Login;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/address")
@Login(authority = APIAuthorityEnum.OnlyUser)
public class AddressRestController extends ApiBaseRestController {

    @Autowired
    GDMapManager gdMapManager;

    @Autowired
    EMapper  eMapper;

    @Autowired
    ReceiverService  receiverService;

    @Autowired
    ShopService shopService;

    /**
     * 查询地理位置
     * @param keyWord
     * @return
     */
    @GetMapping("/search")
    public R querAddress(String keyWord,String lat,String lng,String adcode){
        String location= lng+","+lat;
        //地址列表
        List<APIGDMapTipVO> list = gdMapManager.queryAddr(keyWord,location,adcode);

        return R.ok().put("data",eMapper.mapArray(list,APIAddressVO.class));
    }

    /**
     * 保存收货地址
     * @param receiverModel
     * @return
     */
    @PostMapping("/")
    public R save(@RequestBody APIReceiverVO receiverModel){

        //微信小程序地址需转换
        if(getBaseModel().getClientEnumType() == PlatformEnum.WechatMiniprogram ){
            String location = gdMapManager.convertLocation(receiverModel.getLat(), receiverModel.getLng(), "gps");
            if(!StringUtils.isEmpty(location)){
                String [] latlng =  location.split(",");
                receiverModel.setLat(latlng[1]);
                receiverModel.setLng(latlng[0]);
            }
        }

        ReceiverDO receiverDO  = eMapper.map(receiverModel,ReceiverDO.class);

        receiverService.save(receiverDO);

        return R.ok();
    }

    @GetMapping("/")
    public R getList(Boolean choise){

        Map<String,Object> params =  new HashMap<>();

        params.put("userId",getBaseModel().getUserId());

        List<ReceiverDO> list = receiverService.list(params);

        List<APIReceiverVO> renList=new ArrayList<>();

        if(list!=null &&  list.size()>0) {
            renList = eMapper.mapArray(list, APIReceiverVO.class);
            //设置是否大于配送范围
            if(choise!=null && choise){
                //查询店铺配送范围
                ShopDO shopDO = shopService.getByCustomerId(getBaseModel().getCustomerId());
                //配送范围
                double range =  shopDO.getDeliveryRange()*1000;
                if(shopDO!=null) {
                    renList.forEach(item -> {
                       double distance = AMapUtils.calculateLineDistance(
                                Double.parseDouble(shopDO.getLng()),
                                Double.parseDouble(shopDO.getLat()),
                                Double.parseDouble(item.getLng()),
                                Double.parseDouble(item.getLat()));
                       //绝对值
                        distance = Math.abs(distance);
                        item.setDeliveryRange((int)distance);
                        item.setOverRange(distance>range);
                    });
                }
            }
        }
        return R.ok().put("data",renList);
    }

}
