package wyx.manager.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import wyx.manager.entity.WyxTempPacketEntity;
import wyx.manager.service.WyxShareServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import wyx.manager.entity.WyxShareEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import wyx.manager.service.WyxTempPacketServiceI;

@Service("wyxShareService")
@Transactional
public class WyxShareServiceImpl extends CommonServiceImpl implements WyxShareServiceI {

	@Autowired
	WyxTempPacketServiceI wyxTempPacketService;
	
 	public void delete(WyxShareEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(WyxShareEntity entity) throws Exception{

		//执行新增操作增强业务
		this.doAddBus(entity);

 		Serializable t = super.save(entity);

 		return t;
 	}
 	
 	public void saveOrUpdate(WyxShareEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}

	@Override
	public Integer save(WyxShareEntity entity, WyxTempPacketEntity tempPacket) throws Exception{

		//保存分享数据
 		Serializable id = save(entity);

 		//设置id相同
		tempPacket.setId((Integer)id);
		tempPacket.setCreateId(entity.getCreateId());
		wyxTempPacketService.save(tempPacket);

		return tempPacket.getId();
	}

	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WyxShareEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
		t.setTDelete(1);
		t.setUpdateDate(new Date());
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WyxShareEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param
	 * @return
	 */
	private void doDelBus(WyxShareEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WyxShareEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("s_title", t.getSTitle());
		map.put("s_describe", t.getSDescribe());
		map.put("icon_src", t.getIconSrc());
		map.put("update_date", t.getUpdateDate());
		map.put("t_delete", t.getTDelete());
		map.put("create_id", t.getCreateId());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WyxShareEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{s_title}",String.valueOf(t.getSTitle()));
 		sql  = sql.replace("#{s_describe}",String.valueOf(t.getSDescribe()));
 		sql  = sql.replace("#{icon_src}",String.valueOf(t.getIconSrc()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{t_delete}",String.valueOf(t.getTDelete()));
 		sql  = sql.replace("#{create_id}",String.valueOf(t.getCreateId()));
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
					javaInter.execute("wyx_share",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}