package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-03-31 20:50:47
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//用户昵称
	private String userNick;
	//手机号
	private String userMobile;
	//头像
	private String userIcon;
	//1:未知、2：男、3：女
	private Integer userSex;
	//1:有效、2：禁用
	private Integer userState;
	//是否删除 0：否 1：是
	private Integer delete;
	//创建时间 
	private Date createTime;

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
	 * 设置：用户昵称
	 */
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getUserNick() {
		return userNick;
	}
	/**
	 * 设置：手机号
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * 设置：头像
	 */
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	/**
	 * 获取：头像
	 */
	public String getUserIcon() {
		return userIcon;
	}
	/**
	 * 设置：1:未知、2：男、3：女
	 */
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	/**
	 * 获取：1:未知、2：男、3：女
	 */
	public Integer getUserSex() {
		return userSex;
	}
	/**
	 * 设置：1:有效、2：禁用
	 */
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	/**
	 * 获取：1:有效、2：禁用
	 */
	public Integer getUserState() {
		return userState;
	}
	/**
	 * 设置：是否删除 0：否 1：是
	 */
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	/**
	 * 获取：是否删除 0：否 1：是
	 */
	public Integer getDelete() {
		return delete;
	}
	/**
	 * 设置：创建时间 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间 
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
