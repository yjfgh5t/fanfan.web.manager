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
 * @Description: 分享的图标
 * @author onlineGenerator
 * @date 2018-01-10 15:13:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_share_icon", schema = "")
@SuppressWarnings("serial")
public class WyxShareIconEntity implements java.io.Serializable {
	/**主键*/

	private Integer id;
	/**排序*/
    @Excel(name="排序",width=15)
		
	private Integer siOrder;
	/**所在分类*/
    @Excel(name="所在分类",width=15,dictTable ="wyx_share_type",dicCode ="id",dicText ="st_name")
		
	private Integer siType;
	/**图标路径*/
    @Excel(name="图标路径",width=15)
	private String siSrc;

	/**是否删除*/
    @Excel(name="是否删除",width=15,dicCode="yesorno")
	private Integer siDelete;
	
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
	 *@return: java.lang.Integer  排序
	 */
	@Column(name ="SI_ORDER",nullable=true,length=20)
	public Integer getSiOrder(){
		return this.siOrder;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSiOrder(Integer siOrder){
		this.siOrder = siOrder;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  所在分类
	 */
	@Column(name ="SI_TYPE",nullable=true,length=20)
	public Integer getSiType(){
		return this.siType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  所在分类
	 */
	public void setSiType(Integer siType){
		this.siType = siType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图标路径
	 */
	@Column(name ="SI_SRC",nullable=true,length=128)
	public String getSiSrc(){
		return this.siSrc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图标路径
	 */
	public void setSiSrc(String siSrc){
		this.siSrc = siSrc;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */
	@Column(name ="SI_DELETE",nullable=true,length=20)
	public Integer getSiDelete(){
		return this.siDelete;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除
	 */
	public void setSiDelete(Integer siDelete){
		this.siDelete = siDelete;
	}
}
