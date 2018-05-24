package com.bootdo.fanfan.web.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
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

import com.bootdo.fanfan.domain.CommoditDO;
import com.bootdo.fanfan.service.CommoditService;
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
public class CommoditController extends BaseController {
	@Autowired
	private CommoditService commoditService;
	
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
		query.put("create_id",getUserId());
		List<CommoditDO> commoditList = commoditService.list(query);
		int total = commoditService.count(query);
		PageUtils pageUtils = new PageUtils(commoditList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:commodit:add")
	String add(){
	    return "fanfan/commodit/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:commodit:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CommoditDO commodit = new CommoditDO();
		commodit.setId(id);
		if(id>0) {
			commodit = commoditService.get(id);
		}
		model.addAttribute("commodit", commodit);
	    return "fanfan/commodit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:commodit:add")
	public R save( CommoditDO commodit){
		if(commoditService.save(commodit)>0){
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
	public R update( CommoditDO commodit){
		if(commodit.getId()==0) {
			commodit.setCreateId(getUserId().intValue());
			commodit.setDelete(0);
			commoditService.save(commodit);
		}else {
			commoditService.update(commodit);
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
		if(commoditService.remove(id)>0){
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
		commoditService.batchRemove(ids);
		return R.ok();
	}
	
}