package com.bootdo.fanfan.service.impl;

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
	
	@Override
	public QrcodeDO get(String id){
		return qrcodeDao.get(id);
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

		QrcodeDO qrcodeDO = get(qrcode.getId());

		if(qrcodeDO==null){
			return -1;
		}

		//不能修改店铺信息
		if(qrcodeDO.getCustomerId()!=null && !qrcodeDO.getCustomerId().equals(qrcode.getCustomerId())){
			throw new SecurityException("不能修改店铺信息");
		}

		qrcode.setModifyTime(new Date());

		return qrcodeDao.update(qrcode);
	}

	/**
	 * 去除客桌对应的二维码
	 * @param customerId
	 * @param deskId
	 * @return
	 */
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
