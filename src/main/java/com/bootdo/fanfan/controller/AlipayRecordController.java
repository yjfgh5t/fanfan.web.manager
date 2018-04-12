package com.bootdo.fanfan.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.fanfan.domain.AlipayRecordDO;
import com.bootdo.fanfan.service.AlipayRecordService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-11 15:33:59
 */
 
@Controller
@RequestMapping("/fanfan/alipayRecord")
public class AlipayRecordController {
	@Autowired
	private AlipayRecordService alipayRecordService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:alipayRecord:alipayRecord")
	String AlipayRecord(){
	    return "fanfan/alipayRecord/alipayRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:alipayRecord:alipayRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AlipayRecordDO> alipayRecordList = alipayRecordService.list(query);
		int total = alipayRecordService.count(query);
		PageUtils pageUtils = new PageUtils(alipayRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:alipayRecord:add")
	String add(){
	    return "fanfan/alipayRecord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:alipayRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AlipayRecordDO alipayRecord = alipayRecordService.get(id);
		model.addAttribute("alipayRecord", alipayRecord);
	    return "fanfan/alipayRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:alipayRecord:add")
	public R save( AlipayRecordDO alipayRecord){
		if(alipayRecordService.save(alipayRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:alipayRecord:edit")
	public R update( AlipayRecordDO alipayRecord){
		alipayRecordService.update(alipayRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:alipayRecord:remove")
	public R remove( Integer id){
		if(alipayRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:alipayRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		alipayRecordService.batchRemove(ids);
		return R.ok();
	}
	
}
