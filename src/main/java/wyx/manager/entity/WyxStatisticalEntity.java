package wyx.manager.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 点击统计
 * @author onlineGenerator
 * @date 2018-01-10 15:10:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_statistical", schema = "")
@SuppressWarnings("serial")
public class WyxStatisticalEntity implements java.io.Serializable {
	/**主键*/

	private Integer id;
	/**分享表主键*/
    @Excel(name="分享表主键",width=15)
		
	private Integer sid;
	/**查看次数*/
    @Excel(name="查看次数",width=15)
		
	private Integer sSee;
	/**点击次数*/
    @Excel(name="点击次数",width=15)
		
	private Integer sClick;
	/**分享次数*/
    @Excel(name="分享次数",width=15)
		
	private Integer sShare;
	/**创建日期*/

	private Date createDate;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID",nullable=false,length=20)
	public Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主键
	 */
	public void setId(Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分享表主键
	 */
	@Column(name ="SID",nullable=true,length=20)
	public Integer getSid(){
		return this.sid;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分享表主键
	 */
	public void setSid(Integer sid){
		this.sid = sid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  查看次数
	 */
	@Column(name ="S_SEE",nullable=true,length=20)
	public Integer getSSee(){
		return this.sSee;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  查看次数
	 */
	public void setSSee(Integer sSee){
		this.sSee = sSee;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  点击次数
	 */
	@Column(name ="S_CLICK",nullable=true,length=20)
	public Integer getSClick(){
		return this.sClick;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  点击次数
	 */
	public void setSClick(Integer sClick){
		this.sClick = sClick;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  分享次数
	 */
	@Column(name ="S_SHARE",nullable=true,length=20)
	public Integer getSShare(){
		return this.sShare;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  分享次数
	 */
	public void setSShare(Integer sShare){
		this.sShare = sShare;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
}
