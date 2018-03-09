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
 * @Description: 模板表
 * @author onlineGenerator
 * @date 2018-01-10 15:12:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_temp", schema = "")
@SuppressWarnings("serial")
public class WyxTempEntity implements java.io.Serializable {
	/**主键*/

	private Integer id;
	/**模板标题*/
    @Excel(name="模板标题",width=15)
		
	private String tTitle;
	/**模板类型*/
    @Excel(name="模板类型",width=15)
		
	private Integer tType;
	/**缩略图*/
    @Excel(name="缩略图",width=15)
		
	private String tIconSrc;
	/**提示图*/
    @Excel(name="提示图",width=15)
		
	private String tSrcarry;
	/**是否删除*/
    @Excel(name="是否删除",width=15)
		
	private Integer tDelete;
	/**更新日期*/

	private Date updateDate;
	
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
	 *@return: java.lang.String  模板标题
	 */
	@Column(name ="T_TITLE",nullable=true,length=32)
	public String getTTitle(){
		return this.tTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模板标题
	 */
	public void setTTitle(String tTitle){
		this.tTitle = tTitle;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  模板类型
	 */
	@Column(name ="T_TYPE",nullable=true,length=20)
	public Integer getTType(){
		return this.tType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  模板类型
	 */
	public void setTType(Integer tType){
		this.tType = tType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缩略图
	 */
	@Column(name ="T_ICON_SRC",nullable=true,length=128)
	public String getTIconSrc(){
		return this.tIconSrc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缩略图
	 */
	public void setTIconSrc(String tIconSrc){
		this.tIconSrc = tIconSrc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提示图
	 */
	@Column(name ="T_SRCARRY",nullable=true,length=512)
	public String getTSrcarry(){
		return this.tSrcarry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提示图
	 */
	public void setTSrcarry(String tSrcarry){
		this.tSrcarry = tSrcarry;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */
	@Column(name ="T_DELETE",nullable=true,length=20)
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
}
