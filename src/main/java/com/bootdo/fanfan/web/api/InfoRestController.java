package com.bootdo.fanfan.web.api;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.DictionaryService;
import com.bootdo.fanfan.service.QrcodeService;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.vo.APIShopVO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CacheConfig(cacheNames = "api_info")
@RequestMapping("/api/info")
public class InfoRestController extends ApiBaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    BootdoConfig bootdoConfig;

    @Autowired
    QrcodeService qrcodeService;

    @Autowired
    private FileService sysFileService;

    @Autowired
    ShopService shopService;

    @Autowired
    EMapper eMapper;

    /**
     * 初始化信息
     * @return
     */
    @GetMapping("/")
    public R initInfo(@RequestParam(required = false) String qrcode){
        Integer customerId = getBaseModel().getCustomerId();

        //返回信息
        Map<String,Object> resultMap = new HashMap<>();

        if(StringUtils.isNotEmpty(qrcode)){
            QrcodeDO qrcodeDO = qrcodeService.get(qrcode);
            if(qrcodeDO.getCustomerId()!=null){
                if(qrcodeDO.getCustomerId()!=null) {
                    customerId = qrcodeDO.getCustomerId();
                }
                //设置桌号
                if(qrcodeDO.getDeskId()!=null) {
                    resultMap.put("deskId", qrcodeDO.getDeskId());
                }
            }
        }

        if(customerId==-1){
            customerId=132;
        }

        ShopDO shopDO = shopService.getByCustomerId(customerId);
        //店铺信息
        if(shopDO!=null){
            APIShopVO shopVO = eMapper.map(shopDO, APIShopVO.class);
            resultMap.put("shop",shopVO);
        }

        //设置customerId
        resultMap.put("customerId",customerId);

        return R.ok().put("data",resultMap);
    }

    /**
     * 获取最新html版本
     * @return
     */
    @GetMapping("/version")
    public R htmlVersion(){
        Map<Integer,String>  dicMap = dictionaryService.queryByKeys(0,DictionaryEnum.htmlVersion.getVal());

        return R.ok().put("data",dicMap.get(DictionaryEnum.htmlVersion.getVal()));
    }

    /**
     * 下载新版本
     * @return
     */
    @GetMapping("/www.zip")
    public ResponseEntity<byte[]> downLoadZip(){

       byte[] fileBytes = FileUtil.readByBytes(bootdoConfig.getUploadPath()+"www.zip");

       return  new ResponseEntity<byte[]>(fileBytes, HttpStatus.OK);
    }

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
            String [] tempName= fileName.split("\\.");
            //缩小图片
            if(tempName.length==2){
                fileName = tempName[0] + ".min." + tempName[1];
                Thumbnails.of(file.getInputStream()).scale(0.6f).outputQuality(0.5).toFile(bootdoConfig.getUploadPath() + fileName);
            }
        } catch (Exception e) {
            return R.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("data",bootdoConfig.getStaticUrl()+ fileName);
        }
        return R.error();
    }

    /**
     * 扫码入口
     * @param qrCodeId
     * @return
     */
    @GetMapping("/qrcode/{qrcode}")
    @Cacheable(value = "qrcode",keyGenerator = "keyGenerator")
    public R qrcode(@PathVariable("qrcode") String qrCodeId){
        System.out.println("访问"+qrCodeId);
        return R.ok();
    }
}
