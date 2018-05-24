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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.fanfan.domain.OrderStateDO;
import com.bootdo.fanfan.service.OrderStateService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:31:20
 */
 
@Controller
@RequestMapping("/fanfan/orderState")
public class OrderStateController {
	@Autowired
	private OrderStateService orderStateService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:orderState:orderState")
	String OrderState(){
	    return "fanfan/orderState/orderState";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:orderState:orderState")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderStateDO> orderStateList = orderStateService.list(query);
		int total = orderStateService.count(query);
		PageUtils pageUtils = new PageUtils(orderStateList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:orderState:add")
	String add(){
	    return "fanfan/orderState/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:orderState:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderStateDO orderState = orderStateService.get(id);
		model.addAttribute("orderState", orderState);
	    return "fanfan/orderState/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:orderState:add")
	public R save( OrderStateDO orderState){
		if(orderStateService.save(orderState)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:orderState:edit")
	public R update( OrderStateDO orderState){
		//orderStateService.update(orderState);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderState:remove")
	public R remove( Integer id){
		if(orderStateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderState:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderStateService.batchRemove(ids);
		return R.ok();
	}
	
}
