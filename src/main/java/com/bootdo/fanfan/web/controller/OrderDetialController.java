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

import com.bootdo.fanfan.domain.OrderDetialDO;
import com.bootdo.fanfan.service.OrderDetialService;
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
@RequestMapping("/fanfan/orderDetial")
public class OrderDetialController {
	@Autowired
	private OrderDetialService orderDetialService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:orderDetial:orderDetial")
	String OrderDetial(){
	    return "fanfan/orderDetial/orderDetial";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:orderDetial:orderDetial")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDetialDO> orderDetialList = orderDetialService.list(query);
		int total = orderDetialService.count(query);
		PageUtils pageUtils = new PageUtils(orderDetialList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:orderDetial:add")
	String add(){
	    return "fanfan/orderDetial/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:orderDetial:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderDetialDO orderDetial = orderDetialService.get(id);
		model.addAttribute("orderDetial", orderDetial);
	    return "fanfan/orderDetial/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:orderDetial:add")
	public R save( OrderDetialDO orderDetial){
		if(orderDetialService.save(orderDetial)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:orderDetial:edit")
	public R update( OrderDetialDO orderDetial){
		orderDetialService.update(orderDetial);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderDetial:remove")
	public R remove( Integer id){
		if(orderDetialService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:orderDetial:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderDetialService.batchRemove(ids);
		return R.ok();
	}
	
}
