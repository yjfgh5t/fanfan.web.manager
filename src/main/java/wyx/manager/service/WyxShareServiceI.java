package wyx.manager.service;
import wyx.manager.entity.WyxShareEntity;
import org.jeecgframework.core.common.service.CommonService;
import wyx.manager.entity.WyxTempPacketEntity;

import java.io.Serializable;

public interface WyxShareServiceI extends CommonService{
	
 	public void delete(WyxShareEntity entity) throws Exception;
 	
 	public Serializable save(WyxShareEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxShareEntity entity) throws Exception;

	Integer save(WyxShareEntity entity ,WyxTempPacketEntity tempPacket) throws Exception;
 	
}
