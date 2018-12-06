package com.bootdo.fanfan.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.fanfan.domain.CommodityCategoryDO;
import com.bootdo.fanfan.domain.CommodityDO;
import com.bootdo.fanfan.service.CommodityCategoryService;
import com.bootdo.fanfan.service.CommodityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-30 22:53:17
 */
 
@Controller
@RequestMapping("/fanfan/commodit")
public class CommodityController extends BaseController {
	@Autowired
	private CommodityService commodityService;

	@Autowired
	private CommodityCategoryService commodityCategoryService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:commodit:commodit")
	String Commodit(){
	    return "fanfan/commodit/commodit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:commodit:commodit")
	public PageUtils list(@RequestParam Map<String, Object> params){

		//查询列表数据
        Query query = new Query(params);
		query.put("sort","`order`");
		query.put("order","asc");
		List<CommodityDO> commoditList = commodityService.list(query);
		int total = commodityService.count(query);
		PageUtils pageUtils = new PageUtils(commoditList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:commodit:add")
	String add(ModelMap modelMap){

		//查询分类列表
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("customer_id",this.getUserId().intValue());
		List<CommodityCategoryDO> categoryDOList = commodityCategoryService.list(queryParams);

		modelMap.put("categoryTypes",categoryDOList);

		return "fanfan/commodit/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:commodit:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommodityDO commodit =null;
		if(id>0) {
			commodit = commodityService.get(id);
		}

		if(commodit==null){
			commodit = new CommodityDO();
			commodit.setId(id);
		}

		model.addAttribute("commodit", commodit);

		//查询分类列表
		Map<String,Object> queryParams = new HashMap<>();
		queryParams.put("customer_id",this.getUserId().intValue());
		List<CommodityCategoryDO> categoryDOList = commodityCategoryService.list(queryParams);
		model.addAttribute("categoryTypes",categoryDOList);

	    return "fanfan/commodit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:commodit:add")
	public R save( CommodityDO commodit){
		if(commodityService.save(commodit)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:commodit:edit")
	public R update( CommodityDO commodit){
		if(commodit.getId()==0) {
			commodit.setCustomerId(getUserId().intValue());
			commodit.setDelete(0);
			commodityService.save(commodit);
		}else {
			commodityService.update(commodit);
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:commodit:remove")
	public R remove( Integer id){
		if(commodityService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:commodit:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		commodityService.batchRemove(ids);
		return R.ok();
	}
	
}
