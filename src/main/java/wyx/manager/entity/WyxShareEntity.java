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
 * @Description: 分享主表
 * @author onlineGenerator
 * @date 2018-01-10 15:12:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_share", schema = "")
@SuppressWarnings("serial")
public class WyxShareEntity implements java.io.Serializable {
	/**主键*/

	private Integer id;
	/**分享标题*/
    @Excel(name="分享标题",width=15)
		
	private String sTitle;
	/**描述*/
    @Excel(name="描述",width=15)
		
	private String sDescribe;
	/**图标地址*/
    @Excel(name="图标地址",width=15)
		
	private String iconSrc;
	/**更新日期*/

	private Date updateDate;
	/**是否删除*/

	private Integer tDelete;
	/**创建人id*/
    @Excel(name="创建人id",width=15)
		
	private String createId;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分享标题
	 */
	@Column(name ="S_TITLE",nullable=true,length=64)
	public String getSTitle(){
		return this.sTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分享标题
	 */
	public void setSTitle(String sTitle){
		this.sTitle = sTitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="S_DESCRIBE",nullable=true,length=128)
	public String getSDescribe(){
		return this.sDescribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setSDescribe(String sDescribe){
		this.sDescribe = sDescribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标地址
	 */
	@Column(name ="ICON_SRC",nullable=true,length=128)
	public String getIconSrc(){
		return this.iconSrc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标地址
	 */
	public void setIconSrc(String iconSrc){
		this.iconSrc = iconSrc;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */
	@Column(name ="T_DELETE",nullable=false,length=20)
	public Integer getTDelete(){
		return this.tDelete;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除
	 */
	public void setTDelete(Integer tDelete){
		this.tDelete = tDelete;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人id
	 */
	@Column(name ="CREATE_ID",nullable=false,length=36)
	public String getCreateId(){
		return this.createId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人id
	 */
	public void setCreateId(String createId){
		this.createId = createId;
	}
}
