package wyx.manager.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import wyx.manager.service.WyxTempPacketServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import wyx.manager.entity.WyxTempPacketEntity;
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

@Service("wyxTempPacketService")
@Transactional
public class WyxTempPacketServiceImpl extends CommonServiceImpl implements WyxTempPacketServiceI {
	
 	public void delete(WyxTempPacketEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(WyxTempPacketEntity entity) throws Exception{
		//执行新增操作增强业务
		this.doAddBus(entity);
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(WyxTempPacketEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(WyxTempPacketEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
		t.setTpDelete(1);
		t.setUpdateDate(new Date());
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(WyxTempPacketEntity t) throws Exception{
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
	private void doDelBus(WyxTempPacketEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(WyxTempPacketEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("tp_token", t.getTpToken());
		map.put("tp_copy_msg", t.getTpCopyMsg());
		map.put("tp_share_msg", t.getTpShareMsg());
		map.put("tp_delete", t.getTpDelete());
		map.put("create_id", t.getCreateId());
		map.put("update_date", t.getUpdateDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,WyxTempPacketEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{tp_token}",String.valueOf(t.getTpToken()));
 		sql  = sql.replace("#{tp_copy_msg}",String.valueOf(t.getTpCopyMsg()));
 		sql  = sql.replace("#{tp_share_msg}",String.valueOf(t.getTpShareMsg()));
 		sql  = sql.replace("#{tp_delete}",String.valueOf(t.getTpDelete()));
 		sql  = sql.replace("#{create_id}",String.valueOf(t.getCreateId()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
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
					javaInter.execute("wyx_temp_packet",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}