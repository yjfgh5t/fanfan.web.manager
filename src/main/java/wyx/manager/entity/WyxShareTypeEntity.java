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
 * @Description: 分享类型
 * @author onlineGenerator
 * @date 2018-01-10 15:13:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_share_type", schema = "")
@SuppressWarnings("serial")
public class WyxShareTypeEntity implements java.io.Serializable {
	/**主键*/

	private Integer id;
	/**名称*/
    @Excel(name="名称",width=15)
		
	private String stName;
	/**1.icon 2.temp*/
    @Excel(name="1.icon 2.temp",width=15)
		
	private String stType;
	/**是否删除*/
    @Excel(name="是否删除",width=15,dicCode="yesorno")
		
	private Integer stDelete;
	
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
	 *@return: java.lang.String  名称
	 */
	@Column(name ="ST_NAME",nullable=true,length=32)
	public String getStName(){
		return this.stName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setStName(String stName){
		this.stName = stName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  1.icon 2.temp
	 */
	@Column(name ="ST_TYPE",nullable=true,length=32)
	public String getStType(){
		return this.stType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  1.icon 2.temp
	 */
	public void setStType(String stType){
		this.stType = stType;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */
	@Column(name ="ST_DELETE",nullable=true,length=20)
	public Integer getStDelete(){
		return this.stDelete;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除
	 */
	public void setStDelete(Integer stDelete){
		this.stDelete = stDelete;
	}
}
