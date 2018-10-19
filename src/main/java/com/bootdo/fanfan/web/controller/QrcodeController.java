package com.bootdo.fanfan.web.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.QrCodeUtils;
import com.google.zxing.WriterException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.service.QrcodeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-31 09:44:20
 */
 
@Controller
@RequestMapping("/fanfan/qrcode")
public class QrcodeController {
	@Autowired
	private QrcodeService qrcodeService;

	@Autowired
	BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("fanfan:qrcode:qrcode")
	String Qrcode(){
	    return "fanfan/qrcode/qrcode";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:qrcode:qrcode")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<QrcodeDO> qrcodeList = qrcodeService.list(query);
		int total = qrcodeService.count(query);
		PageUtils pageUtils = new PageUtils(qrcodeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:qrcode:add")
	String add(){
	    return "fanfan/qrcode/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:qrcode:edit")
	String edit(@PathVariable("id") String id,Model model){
		QrcodeDO qrcode = qrcodeService.get(id);
		model.addAttribute("qrcode", qrcode);
	    return "fanfan/qrcode/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:qrcode:add")
	public R save( QrcodeDO qrcode){
		if(qrcodeService.save(qrcode)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:qrcode:edit")
	public R update( QrcodeDO qrcode){
		qrcodeService.update(qrcode);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:qrcode:remove")
	public R remove( String id){
		if(qrcodeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:qrcode:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		qrcodeService.batchRemove(ids);
		return R.ok();
	}

	@RequestMapping("/qrcode")
	public void getQRCode(String context,Integer width, HttpServletResponse response) throws IOException, WriterException {
		try {
			byte[] data = QrCodeUtils.createQrCode(width, URLDecoder.decode(context),"png");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
