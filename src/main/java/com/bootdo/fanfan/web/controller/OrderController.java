package com.bootdo.fanfan.web.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.extend.EMapper;
import com.bootdo.fanfan.domain.OrderDetailDO;
import com.bootdo.fanfan.domain.OrderReceiverDO;
import com.bootdo.fanfan.domain.UserDO;
import com.bootdo.fanfan.domain.enumDO.OrderStateEnum;
import com.bootdo.fanfan.service.OrderDetialService;
import com.bootdo.fanfan.service.OrderReceiverService;
import com.bootdo.fanfan.service.UserService;
import com.bootdo.fanfan.vo.view.OrderVO;
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

import com.bootdo.fanfan.domain.OrderDO;
import com.bootdo.fanfan.service.OrderService;
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
@RequestMapping("/fanfan/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetialService orderDetialService;

	@Autowired
	private OrderReceiverService orderReceiverService;

	@Autowired
	private UserService userService;

	@Autowired
	private EMapper eMapper;
	
	@GetMapping()
	@RequiresPermissions("fanfan:order:order")
	String Order(){
	    return "fanfan/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

        //管理员可查看所有订单
        if(getUserId()!=1) {
			query.put("customerId", getUserId());
		}
		query.put("sort","`create_time`");
		query.put("create_time","desc");
		List<OrderDO> orderList = orderService.list(query);

		//转换VO
		List<OrderVO> orderVOList = eMapper.mapArray(orderList,OrderVO.class);

		//设置状态Text
		if(orderVOList!=null){
			orderVOList.forEach((item)->{
				item.setOrderStateText(OrderStateEnum.get(item.getOrderState()).getText());
			});
		}

		//查询总条数
		int total = orderService.count(query);

		//返回分页
		PageUtils pageUtils = new PageUtils(orderVOList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:order:add")
	String add(){
	    return "fanfan/order/add";
	}

	@GetMapping("/detail/{id}")
	@RequiresPermissions("fanfan:order:detail")
	String edit(@PathVariable("id") Integer id,Model model){

		//订单信息
		OrderDO order = orderService.get(id);

		//未找到订单信息
		if(order==null){
			throw new NullPointerException("未找到订单");
		}

		//订单详情
		List<OrderDetailDO> detialDOList = orderDetialService.queryByOrderId(id);

		//收件人信息
		OrderReceiverDO receiverDO = orderReceiverService.queryById(id);
		if(receiverDO==null){
			receiverDO = new OrderReceiverDO();
			receiverDO.setAddr("");
			receiverDO.setAddrDetail("");
		}

		//下单人信息
		UserDO userDO =userService.get(order.getCustomerId());


		OrderVO orderVO = eMapper.map(order,OrderVO.class);
		//设置订单状态Text
		orderVO.setOrderStateText(OrderStateEnum.get(orderVO.getId()).getText());
		//订单支付类型
		if(orderVO.getOrderPayType()!=null) {
			switch (orderVO.getOrderPayType()) {
				case 1:
					orderVO.setOrderPayTypeText("支付宝");
					break;
				case 2:
					orderVO.setOrderPayTypeText("微信");
					break;
				default:
					orderVO.setOrderPayTypeText("现金");
					break;
			}
		}else {
			orderVO.setOrderPayTypeText("未知");
		}

		model.addAttribute("order", orderVO);
		model.addAttribute("detialArray",detialDOList);
		model.addAttribute("receiver",receiverDO);
		model.addAttribute("user",userDO);

	    return "fanfan/order/detail";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:order:remove")
	public R remove( Integer id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:order:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
