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

import com.bootdo.fanfan.domain.TokenDO;
import com.bootdo.fanfan.service.TokenService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-11-05 14:23:08
 */
 
@Controller
@RequestMapping("/fanfan/token")
public class TokenController {
	@Autowired
	private TokenService tokenService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:token:token")
	String Token(){
	    return "fanfan/token/token";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:token:token")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TokenDO> tokenList = tokenService.list(query);
		int total = tokenService.count(query);
		PageUtils pageUtils = new PageUtils(tokenList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:token:add")
	String add(){
	    return "fanfan/token/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:token:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TokenDO token = tokenService.get(id);
		model.addAttribute("token", token);
	    return "fanfan/token/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:token:add")
	public R save( TokenDO token){
		if(tokenService.save(token)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:token:edit")
	public R update( TokenDO token){
		tokenService.update(token);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:token:remove")
	public R remove( Integer id){
		if(tokenService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:token:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tokenService.batchRemove(ids);
		return R.ok();
	}
	
}
