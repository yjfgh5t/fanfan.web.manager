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

import com.bootdo.fanfan.domain.OrderAlipayDO;
import com.bootdo.fanfan.service.OrderAlipayService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-09 22:34:51
 */
 
@Controller
@RequestMapping("/fanfan/orderAlipay")
public class OrderAlipayController {
	@Autowired
	private OrderAlipayService orderAlipayService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:orderAlipay:orderAlipay")
	String OrderAlipay(){
	    return "fanfan/orderAlipay/orderAlipay";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:orderAlipay:orderAlipay")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderAlipayDO> orderAlipayList = orderAlipayService.list(query);
		int total = orderAlipayService.count(query);
		PageUtils pageUtils = new PageUtils(orderAlipayList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:orderAlipay:add")
	String add(){
	    return "fanfan/orderAlipay/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:orderAlipay:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderAlipayDO orderAlipay = orderAlipayService.get(id);
		model.addAttribute("orderAlipay", orderAlipay);
	    return "fanfan/orderAlipay/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:orderAlipay:add")
	public R save( OrderAlipayDO orderAlipay){
		if(orderAlipayService.save(orderAlipay)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:orderAlipay:edit")
	public R update( OrderAlipayDO orderAlipay){
		orderAlipayService.update(orderAlipay);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderAlipay:remove")
	public R remove( Integer id){
		if(orderAlipayService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderAlipay:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderAlipayService.batchRemove(ids);
		return R.ok();
	}
	
}
