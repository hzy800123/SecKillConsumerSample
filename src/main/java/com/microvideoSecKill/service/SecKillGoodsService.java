package com.microvideoSecKill.service;

import com.microvideoSecKill.dao.SecKillGoodsDao;
import com.microvideoSecKill.domain.SecKillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecKillGoodsService {
	
	@Autowired
	SecKillGoodsDao secKillGoodsDao;

	public List<SecKillGoods> getAllGoodsStockCount() {
		return secKillGoodsDao.getAllGoodsStockCount();
	}

	public boolean createNewSecKillGoodsAndStockCount(String goodsId, Integer stockCount) {
		int result = secKillGoodsDao.createNewSecKillGoodsAndStockCount(goodsId, stockCount);
		return result > 0;
	}

	public boolean reduceStock(String secKillGoodsId, Integer buyCount) {
		int result = secKillGoodsDao.reduceStock(secKillGoodsId, buyCount);
		return result > 0;
	}


	public boolean resetStockCount(String secKillGoodsId, Integer stockCount) {
		int result = secKillGoodsDao.resetStockCount(secKillGoodsId, stockCount);
		return result > 0;
	}
}
