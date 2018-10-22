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

import com.bootdo.fanfan.domain.ContactDO;
import com.bootdo.fanfan.service.ContactService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-22 11:16:13
 */
 
@Controller
@RequestMapping("/fanfan/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:contact:contact")
	String Contact(){
	    return "fanfan/contact/contact";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:contact:contact")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContactDO> contactList = contactService.list(query);
		int total = contactService.count(query);
		PageUtils pageUtils = new PageUtils(contactList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:contact:add")
	String add(){
	    return "fanfan/contact/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:contact:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ContactDO contact = contactService.get(id);
		model.addAttribute("contact", contact);
	    return "fanfan/contact/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:contact:add")
	public R save( ContactDO contact){
		if(contactService.save(contact)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:contact:edit")
	public R update( ContactDO contact){
		contactService.update(contact);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:contact:remove")
	public R remove( Integer id){
		if(contactService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:contact:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		contactService.batchRemove(ids);
		return R.ok();
	}
	
}
