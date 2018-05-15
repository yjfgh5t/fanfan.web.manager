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

import com.bootdo.fanfan.domain.DictionaryDO;
import com.bootdo.fanfan.service.DictionaryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-15 21:29:01
 */
 
@Controller
@RequestMapping("/fanfan/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:dictionary:dictionary")
	String Dictionary(){
	    return "fanfan/dictionary/dictionary";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:dictionary:dictionary")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DictionaryDO> dictionaryList = dictionaryService.list(query);
		int total = dictionaryService.count(query);
		PageUtils pageUtils = new PageUtils(dictionaryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:dictionary:add")
	String add(){
	    return "fanfan/dictionary/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:dictionary:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DictionaryDO dictionary = dictionaryService.get(id);
		model.addAttribute("dictionary", dictionary);
	    return "fanfan/dictionary/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:dictionary:add")
	public R save( DictionaryDO dictionary){
		if(dictionaryService.save(dictionary)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:dictionary:edit")
	public R update( DictionaryDO dictionary){
		dictionaryService.update(dictionary);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:dictionary:remove")
	public R remove( Integer id){
		if(dictionaryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:dictionary:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dictionaryService.batchRemove(ids);
		return R.ok();
	}
	
}
