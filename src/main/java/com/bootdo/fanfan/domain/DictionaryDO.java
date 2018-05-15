package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-15 21:29:01
 */
public class DictionaryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//1011:营业开始时间  1012:营业结束时间   1021:店铺名称  1022:最低起送价  
	private Integer key;
	//
	private String val;
	//
	private Integer createId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：1011:营业开始时间  1012:营业结束时间   1021:店铺名称  1022:最低起送价  
	 */
	public void setKey(Integer key) {
		this.key = key;
	}
	/**
	 * 获取：1011:营业开始时间  1012:营业结束时间   1021:店铺名称  1022:最低起送价  
	 */
	public Integer getKey() {
		return key;
	}
	/**
	 * 设置：
	 */
	public void setVal(String val) {
		this.val = val;
	}
	/**
	 * 获取：
	 */
	public String getVal() {
		return val;
	}
	/**
	 * 设置：
	 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateId() {
		return createId;
	}
}
