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

import com.bootdo.fanfan.domain.ShopDO;
import com.bootdo.fanfan.service.ShopService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-12 17:10:19
 */
 
@Controller
@RequestMapping("/fanfan/shop")
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:shop:shop")
	String Shop(){
	    return "fanfan/shop/shop";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:shop:shop")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ShopDO> shopList = shopService.list(query);
		int total = shopService.count(query);
		PageUtils pageUtils = new PageUtils(shopList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:shop:add")
	String add(){
	    return "fanfan/shop/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:shop:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ShopDO shop = shopService.get(id);
		model.addAttribute("shop", shop);
	    return "fanfan/shop/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:shop:add")
	public R save( ShopDO shop){
		if(shopService.save(shop)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:shop:edit")
	public R update( ShopDO shop){
		shopService.update(shop);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:shop:remove")
	public R remove( Integer id){
		if(shopService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:shop:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		shopService.batchRemove(ids);
		return R.ok();
	}
	
}
