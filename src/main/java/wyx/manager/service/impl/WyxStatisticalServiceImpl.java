package wyx.manager.service.impl;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import wyx.manager.common.LocalCache;
import wyx.manager.extend.EMapper;
import wyx.manager.service.WyxStatisticalServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import wyx.manager.entity.WyxStatisticalEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import wyx.manager.vo.StatisticalVO;

@Service("wyxStatisticalService")
@Transactional
public class WyxStatisticalServiceImpl extends CommonServiceImpl implements WyxStatisticalServiceI {

	private static final Logger logger = Logger.getLogger(WyxStatisticalServiceImpl.class);

	@Autowired
	EMapper mapper;

 	public void delete(WyxStatisticalEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(WyxStatisticalEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(WyxStatisticalEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}

	/**
	 * 更新缓存数据
	 */
	public int updateCacheData(){

		Map<String,StatisticalVO> listArray = LocalCache.findVal("key_share_");

		//判断是否为空
		if(listArray==null || listArray.size()==0){ return 0;}

		logger.info("更新缓存数据总条数："+listArray.size());

		//临时数据
		WyxStatisticalEntity wyxStatisticalModel =null;
		StatisticalVO item=null;

		//数据转换
		for(String key:listArray.keySet())
		{
			item = listArray.get(key);
			wyxStatisticalModel = mapper.map(item,WyxStatisticalEntity.class);
			wyxStatisticalModel.setCreateDate(item.getCreateDate().toDate());
			try {
				//保存数据
				save(wyxStatisticalModel);
				//删除缓存
				LocalCache.remove(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listArray.size();
	}

 	//region 点击数

	/**
	 * 增加点击数
	 * @param shareId
	 */
	public void addClickCount(int shareId)
	{
		//获取缓存Model
		StatisticalVO statisModel = getCacheModel(shareId);

		//增加查看数量
		statisModel.setSClick(statisModel.getSClick()+1);

		//更新缓存
		updateCacheModel(statisModel);
	}

	/**
	 * 增加查看数量
	 * @param shareId
	 */
	public void addSeeCount(int shareId)
	{
		//获取缓存Model
		StatisticalVO statisModel = getCacheModel(shareId);

		statisModel.setSSee(statisModel.getSSee()+1);

		//更新缓存
		updateCacheModel(statisModel);
	}

	/**
	 * 获取缓存中的数据
	 * @param shareId
	 * @return
	 */
	private StatisticalVO getCacheModel(int shareId){

		DateTime time = new DateTime();

		//点击次数统计
		StatisticalVO statisModel = (StatisticalVO) LocalCache.get("key_share_"+time.toString("MM_HH_")+shareId);

		if(statisModel==null)
		{
			statisModel = new StatisticalVO();
			statisModel.setCreateDate(time);
			statisModel.setSid(shareId);
			statisModel.setSClick(0);
			statisModel.setSSee(0);
		}

		return statisModel;
	}

	/**
	 * 更新缓存
	 * @param model
	 */
	private void updateCacheModel(StatisticalVO model){
		LocalCache.add("key_share_"+model.getCreateDate().toString("MM_HH_")+model.getSid(),model);
	}

	//endregion


 	//region 私有方法
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WyxStatisticalEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WyxStatisticalEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(WyxStatisticalEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WyxStatisticalEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("sid", t.getSid());
		map.put("s_see", t.getSSee());
		map.put("s_click", t.getSClick());
		map.put("s_share", t.getSShare());
		map.put("create_date", t.getCreateDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WyxStatisticalEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{sid}",String.valueOf(t.getSid()));
 		sql  = sql.replace("#{s_see}",String.valueOf(t.getSSee()));
 		sql  = sql.replace("#{s_click}",String.valueOf(t.getSClick()));
 		sql  = sql.replace("#{s_share}",String.valueOf(t.getSShare()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("wyx_statistical",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

 	//endregion
}