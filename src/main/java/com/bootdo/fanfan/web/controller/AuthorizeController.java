package com.bootdo.fanfan.web.controller;

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

import com.bootdo.fanfan.domain.AuthorizeDO;
import com.bootdo.fanfan.service.AuthorizeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-06 23:51:38
 */
 
@Controller
@RequestMapping("/fanfan/authorize")
public class AuthorizeController {
	@Autowired
	private AuthorizeService authorizeService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:authorize:authorize")
	String Authorize(){
	    return "fanfan/authorize/authorize";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:authorize:authorize")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AuthorizeDO> authorizeList = authorizeService.list(query);
		int total = authorizeService.count(query);
		PageUtils pageUtils = new PageUtils(authorizeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:authorize:add")
	String add(){
	    return "fanfan/authorize/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:authorize:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AuthorizeDO authorize = authorizeService.get(id);
		model.addAttribute("authorize", authorize);
	    return "fanfan/authorize/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:authorize:add")
	public R save( AuthorizeDO authorize){
		if(authorizeService.save(authorize)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:authorize:edit")
	public R update( AuthorizeDO authorize){
		authorizeService.update(authorize);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:authorize:remove")
	public R remove( Integer id){
		if(authorizeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:authorize:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		authorizeService.batchRemove(ids);
		return R.ok();
	}
	
}
