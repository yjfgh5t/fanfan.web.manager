package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.DTO.QRCodeDeskDTO;
import com.bootdo.fanfan.domain.QrcodeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-31 09:44:20
 */
@Mapper
public interface QrcodeDao {

	QrcodeDO get(String id);
	
	List<QrcodeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QrcodeDO qrcode);
	
	int update(QrcodeDO qrcode);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	/**
	 * 去除desk标记的二维码
	 * @param customerId
	 * @param deskId
	 * @return
	 */
	@Update("update ff_qrcode set desk_id = 0 where customer_id=#{customerId} and desk_id=${deskId}")
	int removeOldDesk(@Param("customerId") Integer customerId,@Param("deskId") Integer deskId);

	@Select("SELECT qr.customer_id,qr.desk_id,qr.id,de.title as deskText from ff_qrcode as qr LEFT JOIN ff_desk de on qr.desk_id = de.id where qr.id = #{id} limit 1")
	QRCodeDeskDTO getById(@Param("id") String id);
}
