package com.microvideoSecKill.dao;

import com.microvideoSecKill.domain.SecKillGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface SecKillGoodsDao {
	@Select("select * from seckill_goods")
	@Results
	public List<SecKillGoods> getAllGoodsStockCount();

	@Insert("insert into seckill_goods (secKillGoodsId, stockCount) Values(#{goodsId}, #{stockCount})")
	public int createNewSecKillGoodsAndStockCount(String goodsId, Integer stockCount);

	@Update("update seckill_goods set stockCount = stockCount - #{buyCount} where secKillGoodsId = #{goodsId} " +
			"and (stockCount - #{buyCount}) >= 0")
	public int reduceStock(String goodsId, Integer buyCount);

	@Update("update seckill_goods set stockCount = #{stockCount} where secKillGoodsId = #{goodsId}")
	public int resetStockCount(String goodsId, Integer stockCount);
}
