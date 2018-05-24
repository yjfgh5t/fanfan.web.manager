package com.bootdo.fanfan.web.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.fanfan.domain.UserDO;
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
@RequestMapping("/fanfan/user")
public class FFUserController {
	@Autowired
	private UserService userService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:user:user")
	String User(){
	    return "fanfan/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:user:add")
	String add(){
	    return "fanfan/user/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:user:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "fanfan/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:user:add")
	public R save( UserDO user){
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:user:edit")
	public R update( UserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:user:remove")
	public R remove( Integer id){
		if(userService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
