package com.bootdo.fanfan.web.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.config.AlipayConfig;
import com.bootdo.fanfan.manager.AlipayManager;
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

import com.bootdo.fanfan.domain.AlipayKeyDO;
import com.bootdo.fanfan.service.AlipayKeyService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-18 14:36:00
 */
 
@Controller
@RequestMapping("/fanfan/alipayKey")
public class AlipayKeyController {
	@Autowired
	private AlipayKeyService alipayKeyService;

	@Autowired
	private AlipayConfig alipayConfig;

	@GetMapping()
	@RequiresPermissions("fanfan:alipayKey:alipayKey")
	String AlipayKey(){
	    return "fanfan/alipayKey/alipayKey";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:alipayKey:alipayKey")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AlipayKeyDO> alipayKeyList = alipayKeyService.list(query);
		int total = alipayKeyService.count(query);
		PageUtils pageUtils = new PageUtils(alipayKeyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:alipayKey:add")
	String add(){
	    return "fanfan/alipayKey/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:alipayKey:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AlipayKeyDO alipayKey = alipayKeyService.get(id);
		model.addAttribute("alipayKey", alipayKey);
	    return "fanfan/alipayKey/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:alipayKey:add")
	public R save( AlipayKeyDO alipayKey){
		if(alipayKeyService.save(alipayKey)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:alipayKey:edit")
	public R update( AlipayKeyDO alipayKey){
		alipayKeyService.update(alipayKey);
		//删除
		alipayConfig.removeCustomer(alipayKey.getCustomerId());
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:alipayKey:remove")
	public R remove( Integer id){
		if(alipayKeyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:alipayKey:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		alipayKeyService.batchRemove(ids);
		return R.ok();
	}
	
}
