package com.bootdo.fanfan.manager;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.HttpClientUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.fanfan.vo.APIGDMapTipVO;
import com.bootdo.fanfan.vo.APIGDMapVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GDMapManager {

    private static final String serverUrl="http://restapi.amap.com/v3/assistant/inputtips?parameters";

    private static final String  apiKey="94f2a5f80ebda11b224169079397e9f2";

    /**
     * 查询地理位置
     * @param keyWord
     * @param lat
     * @param lng
     * @return
     */
    public List<APIGDMapTipVO> queryAddr(String keyWord, String lat, String lng,String adcode){

        Map<String,String> params = new HashMap<>();

        params.put("key",apiKey);
        params.put("keywords",keyWord);
        if(StringUtils.isNotEmpty(lat) && StringUtils.isNotEmpty(lng)) {
            params.put("location", lng + "," +lat);
        }
        //城市位置
        if(StringUtils.isNotEmpty(adcode)) {
            params.put("citylimit", "true");
            params.put("adcode", adcode);
        }

        String response = HttpClientUtils.doGet(serverUrl,params);

        APIGDMapVO mapVO = JSON.parseObject(response,APIGDMapVO.class);

        if(mapVO.getTips().size()>5) {
            return mapVO.getTips().subList(0, 5);
        }
        return mapVO.getTips();
    }

}
