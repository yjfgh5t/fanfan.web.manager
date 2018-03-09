package wyx.manager.service;
import wyx.manager.entity.WyxTempEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WyxTempServiceI extends CommonService{
	
 	public void delete(WyxTempEntity entity) throws Exception;
 	
 	public Serializable save(WyxTempEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxTempEntity entity) throws Exception;
 	
}
