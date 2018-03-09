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
 * @Description: 红包模板
 * @author onlineGenerator
 * @date 2018-01-10 15:12:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wyx_temp_packet", schema = "")
@SuppressWarnings("serial")
public class WyxTempPacketEntity implements java.io.Serializable {
	/**主键*/
	private Integer id;
	/**口令*/
    @Excel(name="口令",width=15)
		
	private String tpToken;
	/**复制口令后文案*/
    @Excel(name="复制口令后文案",width=15)
		
	private String tpCopyMsg;
	/**提示分享文案*/
    @Excel(name="提示分享文案",width=15)
		
	private String tpShareMsg;
	/**是否删除*/
    @Excel(name="是否删除",width=15)
		
	private Integer tpDelete;
	/**创建人*/

	private String createId;
	/**更新日期*/

	private Date updateDate;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主键
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE)
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
	 *@return: java.lang.String  口令
	 */
	@Column(name ="TP_TOKEN",nullable=true,length=256)
	public String getTpToken(){
		return this.tpToken;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  口令
	 */
	public void setTpToken(String tpToken){
		this.tpToken = tpToken;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  复制口令后文案
	 */
	@Column(name ="TP_COPY_MSG",nullable=true,length=128)
	public String getTpCopyMsg(){
		return this.tpCopyMsg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  复制口令后文案
	 */
	public void setTpCopyMsg(String tpCopyMsg){
		this.tpCopyMsg = tpCopyMsg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提示分享文案
	 */
	@Column(name ="TP_SHARE_MSG",nullable=true,length=128)
	public String getTpShareMsg(){
		return this.tpShareMsg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提示分享文案
	 */
	public void setTpShareMsg(String tpShareMsg){
		this.tpShareMsg = tpShareMsg;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否删除
	 */
	@Column(name ="TP_DELETE",nullable=true,length=20)
	public Integer getTpDelete(){
		return this.tpDelete;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否删除
	 */
	public void setTpDelete(Integer tpDelete){
		this.tpDelete = tpDelete;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_ID",nullable=true,length=50)
	public String getCreateId(){
		return this.createId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateId(String createId){
		this.createId = createId;
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
