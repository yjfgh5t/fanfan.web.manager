package com.bootdo.fanfan.dao;

import com.bootdo.fanfan.domain.ShopDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 * @author jy
 * @email 1992lcg@163.com
 * @date 2018-09-12 17:10:19
 */
@Mapper
public interface ShopDao {

	ShopDO get(Integer id);
	
	List<ShopDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ShopDO shop);
	
	int update(ShopDO shop);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select * from ff_shop where customer_id =#{customerId}")
	ShopDO getByCustomerId(@Param("customerId") Integer customerId);

	@Select("select name from ff_shop where customer_id =#{customerId}")
	String getNameByCustomerId(@Param("customerId") Integer customerId);

	@Select("select telephone from ff_shop where customer_id =#{customerId}")
	String getTelByCustomerId(@Param("customerId") Integer customerId);

	@Select("select alipay,wechat,offline,takeout from ff_shop where id=#{id}")
	ShopDO getPayType(@Param("id") Integer id);
}
