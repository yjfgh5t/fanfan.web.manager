package com.bootdo.fanfan.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.exception.BDException;
import com.bootdo.fanfan.domain.DTO.QRCodeDeskDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.fanfan.dao.QrcodeDao;
import com.bootdo.fanfan.domain.QrcodeDO;
import com.bootdo.fanfan.service.QrcodeService;



@Service
public class QrcodeServiceImpl implements QrcodeService {
	@Autowired
	private QrcodeDao qrcodeDao;

	@Autowired
	private BootdoConfig bootdoConfig;

	@Override
	public QrcodeDO get(String id){
		return qrcodeDao.get(id);
	}

	@Override
	public QRCodeDeskDTO getById( String id){
		return qrcodeDao.getById(id);
	}

	@Override
	public List<QrcodeDO> list(Map<String, Object> map){
		return qrcodeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qrcodeDao.count(map);
	}
	
	@Override
	public int save(QrcodeDO qrcode){

		qrcode.setId(UUID.randomUUID().toString().replace("-",""));
		qrcode.setCreateTime(new Date());
		qrcode.setModifyTime(new Date());

		return qrcodeDao.save(qrcode);
	}
	
	@Override
	public int update(QrcodeDO qrcode){
		qrcode.setModifyTime(new Date());
		return qrcodeDao.update(qrcode);
	}

	/**
	 * 去除客桌对应的二维码
	 * @param customerId
	 * @param deskId
	 * @return
	 */
	@Override
	public int removeOldDesk(Integer customerId,Integer deskId){
		return qrcodeDao.removeOldDesk(customerId,deskId);
	}
	
	@Override
	public int remove(String id){
		return qrcodeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return qrcodeDao.batchRemove(ids);
	}
	
}
