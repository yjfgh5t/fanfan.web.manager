package com.bootdo.fanfan.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-01 11:02:23
 */
public class TpUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//用户id主键
	private Integer userId;
	//第三方应用Id
	private String tpAppId;
	//1:支付宝 2:微信
	private Integer tpType;
	//用户昵称
	private String tpNick;
	//用户头像
	private String tpIcon;
	//1:未知、2:男、3:女
	private Integer tpSex;
	//用户地址
	private String tpAddr;
	//第三方主键
	private String tpId;
	//所在省份
	private String tpProvince;
	//所在城市
	private String tpCity;
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
	 * 设置：用户id主键
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id主键
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：第三方应用Id
	 */
	public void setTpAppId(String tpAppId) {
		this.tpAppId = tpAppId;
	}
	/**
	 * 获取：第三方应用Id
	 */
	public String getTpAppId() {
		return tpAppId;
	}
	/**
	 * 设置：1:支付宝 2:微信
	 */
	public void setTpType(Integer tpType) {
		this.tpType = tpType;
	}
	/**
	 * 获取：1:支付宝 2:微信
	 */
	public Integer getTpType() {
		return tpType;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setTpNick(String tpNick) {
		this.tpNick = tpNick;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getTpNick() {
		return tpNick;
	}
	/**
	 * 设置：用户头像
	 */
	public void setTpIcon(String tpIcon) {
		this.tpIcon = tpIcon;
	}
	/**
	 * 获取：用户头像
	 */
	public String getTpIcon() {
		return tpIcon;
	}
	/**
	 * 设置：1:未知、2:男、3:女
	 */
	public void setTpSex(Integer tpSex) {
		this.tpSex = tpSex;
	}
	/**
	 * 获取：1:未知、2:男、3:女
	 */
	public Integer getTpSex() {
		return tpSex;
	}
	/**
	 * 设置：用户地址
	 */
	public void setTpAddr(String tpAddr) {
		this.tpAddr = tpAddr;
	}
	/**
	 * 获取：用户地址
	 */
	public String getTpAddr() {
		return tpAddr;
	}
	/**
	 * 设置：第三方主键
	 */
	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
	/**
	 * 获取：第三方主键
	 */
	public String getTpId() {
		return tpId;
	}
	/**
	 * 设置：所在省份
	 */
	public void setTpProvince(String tpProvince) {
		this.tpProvince = tpProvince;
	}
	/**
	 * 获取：所在省份
	 */
	public String getTpProvince() {
		return tpProvince;
	}
	/**
	 * 设置：所在城市
	 */
	public void setTpCity(String tpCity) {
		this.tpCity = tpCity;
	}
	/**
	 * 获取：所在城市
	 */
	public String getTpCity() {
		return tpCity;
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
