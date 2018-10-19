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

import com.bootdo.fanfan.domain.CommodityExtendDO;
import com.bootdo.fanfan.service.CommodityExtendService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-10-19 17:00:04
 */
 
@Controller
@RequestMapping("/fanfan/commodityExtend")
public class CommodityExtendController {
	@Autowired
	private CommodityExtendService commodityExtendService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:commodityExtend:commodityExtend")
	String CommodityExtend(){
	    return "fanfan/commodityExtend/commodityExtend";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:commodityExtend:commodityExtend")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommodityExtendDO> commodityExtendList = commodityExtendService.list(query);
		int total = commodityExtendService.count(query);
		PageUtils pageUtils = new PageUtils(commodityExtendList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:commodityExtend:add")
	String add(){
	    return "fanfan/commodityExtend/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:commodityExtend:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommodityExtendDO commodityExtend = commodityExtendService.get(id);
		model.addAttribute("commodityExtend", commodityExtend);
	    return "fanfan/commodityExtend/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:commodityExtend:add")
	public R save( CommodityExtendDO commodityExtend){
		if(commodityExtendService.save(commodityExtend)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:commodityExtend:edit")
	public R update( CommodityExtendDO commodityExtend){
		commodityExtendService.update(commodityExtend);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:commodityExtend:remove")
	public R remove( Integer id){
		if(commodityExtendService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:commodityExtend:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		commodityExtendService.batchRemove(ids);
		return R.ok();
	}
	
}
