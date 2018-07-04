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

import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.service.DeskService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-07-03 12:18:24
 */
 
@Controller
@RequestMapping("/fanfan/desk")
public class DeskController {
	@Autowired
	private DeskService deskService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:desk:desk")
	String Desk(){
	    return "fanfan/desk/desk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:desk:desk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeskDO> deskList = deskService.list(query);
		int total = deskService.count(query);
		PageUtils pageUtils = new PageUtils(deskList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:desk:add")
	String add(){
	    return "fanfan/desk/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:desk:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DeskDO desk = deskService.get(id);
		model.addAttribute("desk", desk);
	    return "fanfan/desk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:desk:add")
	public R save( DeskDO desk){
		if(deskService.save(desk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:desk:edit")
	public R update( DeskDO desk){
		deskService.update(desk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:desk:remove")
	public R remove( Integer id){
		if(deskService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:desk:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deskService.batchRemove(ids);
		return R.ok();
	}
	
}
