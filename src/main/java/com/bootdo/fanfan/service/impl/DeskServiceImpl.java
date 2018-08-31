package com.bootdo.fanfan.service.impl;

import com.bootdo.fanfan.domain.DTO.DeskDTO;
import com.bootdo.fanfan.domain.DTO.QRCodeDTO;
import com.bootdo.fanfan.manager.AlipayManager;
import com.bootdo.fanfan.vo.APIDeskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.DeskDao;
import com.bootdo.fanfan.domain.DeskDO;
import com.bootdo.fanfan.service.DeskService;



@Service
public class DeskServiceImpl implements DeskService {
	@Autowired
	private DeskDao deskDao;

	@Autowired
	AlipayManager alipayManager;
	
	@Override
	public DeskDO get(Integer id){
		return deskDao.get(id);
	}
	
	@Override
	public List<DeskDO> list(Map<String, Object> map){
		return deskDao.list(map);
	}

	@Override
	public List<DeskDTO> queryList(Integer customerId){

		List<DeskDTO> deskDTOS = deskDao.queryList(customerId);
		if(deskDTOS!=null && deskDTOS.size()>0){
			deskDTOS.forEach((item)->{
				if(item.getQrCodeId()==null){
					item.setQrCodeId("");
				}
			});
		}

		return deskDTOS;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deskDao.count(map);
	}
	
	@Override
	public int save(DeskDO desk){
		return deskDao.save(desk);
	}
	
	@Override
	public int update(DeskDO desk){
		return deskDao.update(desk);
	}
	
	@Override
	public int remove(Integer id){
		return deskDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deskDao.batchRemove(ids);
	}

	@Override
	public String getQRCode(Integer id) {

		DeskDO deskDO = get(id);
		if(deskDO==null){
			return null;
		}

		QRCodeDTO codeDTO = new QRCodeDTO();
		codeDTO.setDescribe(deskDO.getTitle());
		codeDTO.putParam("customerId",deskDO.getCustomerId());
		codeDTO.putParam("deskId",deskDO.getId());
		codeDTO.setUrlParam("pages/index/index");

		//调用接口
		String codeUrl = alipayManager.createQRCode(codeDTO);

		return codeUrl;
	}

}
