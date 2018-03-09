package wyx.manager.service;
import wyx.manager.entity.WyxShareIconEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WyxShareIconServiceI extends CommonService{
	
 	public void delete(WyxShareIconEntity entity) throws Exception;
 	
 	public Serializable save(WyxShareIconEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxShareIconEntity entity) throws Exception;
 	
}
