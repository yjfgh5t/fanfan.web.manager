package com.bootdo.fanfan.service;

import com.bootdo.fanfan.domain.QrcodeDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-08-31 09:44:20
 */
public interface QrcodeService {
	
	QrcodeDO get(String id);
	
	List<QrcodeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QrcodeDO qrcode);
	
	int update(QrcodeDO qrcode);

	int removeOldDesk(Integer customerId,Integer deskId);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
