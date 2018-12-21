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

import com.bootdo.fanfan.domain.DeliveryDO;
import com.bootdo.fanfan.service.DeliveryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-22 22:03:11
 */
 
@Controller
@RequestMapping("/fanfan/delivery")
public class DeliveryController extends BaseController {
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping()
	@RequiresPermissions("fanfan:delivery:delivery")
	String Delivery(){
	    return "fanfan/delivery/delivery";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:delivery:delivery")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		query.put("delete",0);
		query.put("create_id",getUserId());
		List<DeliveryDO> deliveryList = deliveryService.list(query);
		int total = deliveryService.count(query);
		PageUtils pageUtils = new PageUtils(deliveryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:delivery:add")
	String add(){
	    return "fanfan/delivery/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:delivery:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DeliveryDO delivery = deliveryService.get(id);
		model.addAttribute("delivery", delivery);
	    return "fanfan/delivery/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:delivery:add")
	public R save( DeliveryDO delivery){
		delivery.setCustomerId(getUserId().intValue());
		if(deliveryService.save(delivery)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:delivery:edit")
	public R update( DeliveryDO delivery){
		deliveryService.update(delivery);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:delivery:remove")
	public R remove( Integer id){
		if(deliveryService.remove(id)>0){
			return R.ok();
		}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:delivery:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deliveryService.batchRemove(ids);
		return R.ok();
	}
	
}
