package wyx.manager.service;
import wyx.manager.entity.WyxStatisticalEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface WyxStatisticalServiceI extends CommonService{
	
 	public void delete(WyxStatisticalEntity entity) throws Exception;
 	
 	public Serializable save(WyxStatisticalEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WyxStatisticalEntity entity) throws Exception;

	/**
	 * 更新缓存数据
	 */
	int updateCacheData();

	/**
	 * 增加点击数量
	 * @param shareId
	 */
	void addClickCount(int shareId);

	/**
	 * 增加查看数量
	 * @param shareId
	 */
	void addSeeCount(int shareId);
}
