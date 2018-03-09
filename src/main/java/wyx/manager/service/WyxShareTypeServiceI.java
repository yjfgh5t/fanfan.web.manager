package wyx.manager.service;
import wyx.manager.entity.WyxShareTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WyxShareTypeServiceI extends CommonService{
	
 	public void delete(WyxShareTypeEntity entity) throws Exception;
 	
 	public Serializable save(WyxShareTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxShareTypeEntity entity) throws Exception;
 	
}
