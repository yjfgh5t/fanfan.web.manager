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

import com.bootdo.fanfan.domain.TpUserDO;
import com.bootdo.fanfan.service.TpUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:50:47
 */
 
@Controller
@RequestMapping("/fanfan/tpUser")
public class TpUserController {
	@Autowired
	private TpUserService tpUserService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:tpUser:tpUser")
	String TpUser(){
	    return "fanfan/tpUser/tpUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:tpUser:tpUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TpUserDO> tpUserList = tpUserService.list(query);
		int total = tpUserService.count(query);
		PageUtils pageUtils = new PageUtils(tpUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:tpUser:add")
	String add(){
	    return "fanfan/tpUser/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:tpUser:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TpUserDO tpUser = tpUserService.get(id);
		model.addAttribute("tpUser", tpUser);
	    return "fanfan/tpUser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:tpUser:add")
	public R save( TpUserDO tpUser){
		if(tpUserService.save(tpUser)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:tpUser:edit")
	public R update( TpUserDO tpUser){
		tpUserService.update(tpUser);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:tpUser:remove")
	public R remove( Integer id){
		if(tpUserService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:tpUser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tpUserService.batchRemove(ids);
		return R.ok();
	}
	
}
