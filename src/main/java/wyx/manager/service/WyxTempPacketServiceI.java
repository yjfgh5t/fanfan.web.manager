package wyx.manager.service;
import wyx.manager.entity.WyxTempPacketEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WyxTempPacketServiceI extends CommonService{
	
 	public void delete(WyxTempPacketEntity entity) throws Exception;
 	
 	public Serializable save(WyxTempPacketEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxTempPacketEntity entity) throws Exception;
 	
}
