package com.bootdo.fanfan.web.api;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.extend.EMapper;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.fanfan.constant.RedisConstant;
import com.bootdo.fanfan.domain.DTO.QRCodeDeskDTO;
import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.service.DictionaryService;
import com.bootdo.fanfan.service.QrcodeService;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.fanfan.vo.APIShopVO;
import com.google.zxing.WriterException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
    ShopService shopService;

    @Autowired
    EMapper eMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    FileService sysFileService;

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
            QRCodeDeskDTO qrcodeDO = qrcodeService.getById(qrcode);
            if(qrcodeDO.getCustomerId()!=null){
                if(qrcodeDO.getCustomerId()!=null) {
                    customerId = qrcodeDO.getCustomerId();
                }
                //设置桌号
                if(qrcodeDO.getDeskId()!=null) {
                    resultMap.put("deskNum", qrcodeDO.getDeskText());
                }
            }
        }

        if(customerId==-1){
            customerId=1;
        }

        //店铺信息
        ShopDO shopDO = shopService.getByCustomerId(customerId);
        if(shopDO!=null){
            APIShopVO shopVO = eMapper.map(shopDO, APIShopVO.class);
            resultMap.put("shop",shopVO);
        }

        //设置customerId
        resultMap.put("customerId",customerId);

        //配置信息
        Map<Integer,String> configMaps = dictionaryService.queryByKeys(1,DictionaryEnum.showContact.getVal());
        if(configMaps!=null){
            resultMap.put("showContact",configMaps.get(DictionaryEnum.showContact.getVal()));
        }

        return R.ok().put("data",resultMap);
    }

    /**
     * 获取最新html版本
     * @return
     */
    @GetMapping("/version")
    public R htmlVersion(){
        Map<Integer,String>  dicMap = dictionaryService.queryByKeys(1,DictionaryEnum.htmlVersion.getVal(),DictionaryEnum.androidVersion.getVal());

        Map<String,String> result = new HashMap<>();
        result.put(DictionaryEnum.htmlVersion.name(),dicMap.get(DictionaryEnum.htmlVersion.getVal()));
        result.put(DictionaryEnum.androidVersion.name(),dicMap.get(DictionaryEnum.androidVersion.getVal()));

        return R.ok().put("data",result);
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
        fileName = "img/"+FileUtil.renameToUUID(fileName);
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

    @RequestMapping("/qrCodeImg")
    public void getQRCode(String context,Integer width, HttpServletResponse response) throws IOException, WriterException {

            int redirectIndex = context.indexOf("redirect_uri");
            //编码
            if(redirectIndex>0){
                context = context.substring(0,redirectIndex+13) + URLEncoder.encode(context.substring(redirectIndex+13,context.length()),"utf-8");
            }

            byte[] data = QrCodeUtils.createQrCode(width, URLDecoder.decode(context),"png");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
    }

    /**
     * 图片验证码
     * @param mobile
     */
    @GetMapping("/imgcode/{mobile}")
    public void imgCode(@PathVariable("mobile") String mobile, HttpServletResponse response) throws IOException {
        //创建图片验证码
        ImgCodeUtils codeUtils = ImgCodeUtils.build().createCode();
        //获验证码
        String code = codeUtils.getCode();
        //保存至缓存
        redisUtils.hset(RedisConstant.IMG_CODE_KEY,mobile,code,10*60);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(codeUtils.getByte());
        outputStream.flush();
        outputStream.close();
    }
}
