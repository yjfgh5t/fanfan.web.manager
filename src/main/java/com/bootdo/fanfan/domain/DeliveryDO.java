package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-05-22 22:03:11
 */
public class DeliveryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//配送人姓名
	private String name;
	//配送人电话
	private String tel;
	//是否删除
	private Integer delete;
	//创建人
	private Integer createId;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：配送人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：配送人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：配送人电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：配送人电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDelete() {
		return delete;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateId() {
		return createId;
	}
}
