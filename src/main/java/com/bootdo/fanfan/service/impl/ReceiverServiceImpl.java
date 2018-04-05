package com.bootdo.fanfan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.fanfan.dao.ReceiverDao;
import com.bootdo.fanfan.domain.ReceiverDO;
import com.bootdo.fanfan.service.ReceiverService;



@Service
public class ReceiverServiceImpl implements ReceiverService {
	@Autowired
	private ReceiverDao receiverDao;
	
	@Override
	public ReceiverDO get(Integer id){
		return receiverDao.get(id);
	}
	
	@Override
	public List<ReceiverDO> list(Map<String, Object> map){
		return receiverDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return receiverDao.count(map);
	}
	
	@Override
	public int save(ReceiverDO receiver){
		if(receiver.getId()==null || receiver.getId()==0) {
			receiver.setDelete(0);
			return receiverDao.save(receiver);
		}else {
			return update(receiver);
		}
	}
	
	@Override
	public int update(ReceiverDO receiver){
		return receiverDao.update(receiver);
	}
	
	@Override
	public int remove(Integer id){
		return receiverDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return receiverDao.batchRemove(ids);
	}
	
}
