package com.bootdo.fanfan.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.extend.EMapper;
import com.bootdo.fanfan.domain.enumDO.DictionaryEnum;
import com.bootdo.fanfan.vo.view.DictionaryVO;
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

import com.bootdo.fanfan.domain.DictionaryDO;
import com.bootdo.fanfan.service.DictionaryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-15 21:29:01
 */
 
@Controller
@RequestMapping("/fanfan/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;

	@Autowired
	private EMapper eMapper;
	
	@GetMapping()
	@RequiresPermissions("fanfan:dictionary:dictionary")
	String Dictionary(){
	    return "fanfan/dictionary/dictionary";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("fanfan:dictionary:dictionary")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        //获取列表
		List<DictionaryDO> dictionaryList = dictionaryService.list(query);

		//数据转换
		List<DictionaryVO> listDictionvary = eMapper.mapArray(dictionaryList,DictionaryVO.class);

		if(listDictionvary!=null) {
			listDictionvary.forEach((item) -> {
				item.setKeyText(DictionaryEnum.get(item.getKey()).getText());
			});
		}

		//总条数
		int total = dictionaryService.count(query);

		//分页对象
		return new PageUtils(listDictionvary, total);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("fanfan:dictionary:add")
	String add(){
	    return "fanfan/dictionary/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("fanfan:dictionary:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		//获取对象
		DictionaryDO dictionary = dictionaryService.get(id);

		//转换
		DictionaryVO dictionaryVO = eMapper.map(dictionary,DictionaryVO.class);
		dictionaryVO.setKeyText(DictionaryEnum.get(dictionaryVO.getKey()).getText());

		//设置model
		model.addAttribute("dictionary", dictionaryVO);

	    return "fanfan/dictionary/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("fanfan:dictionary:add")
	public R save( DictionaryDO dictionary){
		if(dictionaryService.save(dictionary)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("fanfan:dictionary:edit")
	public R update(DictionaryDO dictionary){
		dictionaryService.update(dictionary);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("fanfan:dictionary:remove")
	public R remove( Integer id){
		if(dictionaryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("fanfan:dictionary:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		dictionaryService.batchRemove(ids);
		return R.ok();
	}
	
}
