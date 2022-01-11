package com.microvideoSecKill.dao;

import com.microvideoSecKill.domain.SecKillOrderDetail;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface SecKillOrderDetailDao {
	@Select("select * from seckill_order_detail")
	@Results
	public List<SecKillOrderDetail> getAllOrderDetail();

	@Insert("insert into seckill_order_detail (userId, goodsId, buyCount, date) Values(#{userId}, #{goodsId}, #{buyCount}, #{timestamp})")
	public int createNewSecKillOrder(String userId, String goodsId, Integer buyCount, Timestamp timestamp);
}
