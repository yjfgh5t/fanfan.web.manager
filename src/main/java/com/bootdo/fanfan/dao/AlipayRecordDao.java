package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.AlipayRecordDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-04-11 15:33:59
 */
@Mapper
public interface AlipayRecordDao {

	AlipayRecordDO get(Integer id);
	
	List<AlipayRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AlipayRecordDO alipayRecord);
	
	int update(AlipayRecordDO alipayRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 根据外部订单号 查询是否存在
	 * @param tradeNo
	 * @return
	 */
	@Select("SELECT count(1) from ff_alipay_record where trade_no=#{tradeNo}")
	int queryByTradeNo(@Param("tradeNo") String tradeNo);

	@Select("SELECT * from ff_alipay_record where out_trade_no=#{outTradeNo}")
	AlipayRecordDO getByOutTradeNo(@Param("outTradeNo") String outTradeNo);

}
