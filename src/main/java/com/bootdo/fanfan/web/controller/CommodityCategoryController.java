package com.bootdo.fanfan.web.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.fanfan.service.CommodityCategoryService;
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

import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-06-20 11:12:04
 */
 
@Controller
@RequestMapping("/fanfan/commoditCategory")
public class CommodityCategoryController extends BaseController {
	@Autowired
	private CommodityCategoryService commodityCategoryService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:commoditCategory:commoditCategory")
	String CommoditCategory(){
	    return "fanfan/commoditCategory/commoditCategory";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:commoditCategory:commoditCategory")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		query.put("sort","`order`");
		query.put("order","asc");
		query.put("delete","0");
		query.put("customerId",getUserId());
		List<CommodityCategoryDO> commoditCategoryList = commodityCategoryService.list(query);
		int total = commodityCategoryService.count(query);
		PageUtils pageUtils = new PageUtils(commoditCategoryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:commoditCategory:add")
	String add(){
	    return "fanfan/commoditCategory/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:commoditCategory:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommodityCategoryDO commoditCategory =null;

		if(id>0) {
			commoditCategory = commodityCategoryService.get(id);
		}

		if(commoditCategory==null) {
			commoditCategory = new CommodityCategoryDO();
			commoditCategory.setId(0);
		}

		model.addAttribute("commoditCategory", commoditCategory);
	    return "fanfan/commoditCategory/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:commoditCategory:add")
	public R save( CommodityCategoryDO commoditCategory){

		if(commodityCategoryService.save(commoditCategory)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:commoditCategory:edit")
	public R update( CommodityCategoryDO commoditCategory){
		if(commoditCategory.getId()==0){
			commoditCategory.setCustomerId(this.getUserId().intValue());
			commodityCategoryService.save(commoditCategory);
		}else {
			commodityCategoryService.update(commoditCategory);
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:commoditCategory:remove")
	public R remove( Integer id){
		if(commodityCategoryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:commoditCategory:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		commodityCategoryService.batchRemove(ids);
		return R.ok();
	}
	
}
