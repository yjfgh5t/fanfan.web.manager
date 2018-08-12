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

import com.bootdo.fanfan.domain.FormIdDO;
import com.bootdo.fanfan.service.FormIdService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-10 17:02:40
 */
 
@Controller
@RequestMapping("/fanfan/formId")
public class FormIdController {
	@Autowired
	private FormIdService formIdService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:formId:formId")
	String FormId(){
	    return "fanfan/formId/formId";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:formId:formId")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FormIdDO> formIdList = formIdService.list(query);
		int total = formIdService.count(query);
		PageUtils pageUtils = new PageUtils(formIdList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:formId:add")
	String add(){
	    return "fanfan/formId/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:formId:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		FormIdDO formId = formIdService.get(id);
		model.addAttribute("formId", formId);
	    return "fanfan/formId/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:formId:add")
	public R save( FormIdDO formId){
		if(formIdService.save(formId)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:formId:edit")
	public R update( FormIdDO formId){
		formIdService.update(formId);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:formId:remove")
	public R remove( Integer id){
		if(formIdService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:formId:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		formIdService.batchRemove(ids);
		return R.ok();
	}
	
}
