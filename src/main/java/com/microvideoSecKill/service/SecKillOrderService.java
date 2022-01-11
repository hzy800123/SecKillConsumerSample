package com.microvideoSecKill.service;

import com.microvideoSecKill.dao.SecKillOrderDetailDao;
import com.microvideoSecKill.domain.SecKillOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SecKillOrderService {
	
	@Autowired
	SecKillOrderDetailDao secKillOrderDetailDao;

	public List<SecKillOrderDetail> getAllOrderDetail() {
		return secKillOrderDetailDao.getAllOrderDetail();
	}

	public boolean createNewSecKillGoodsAndStockCount(String userId, String goodsId, Integer buyCount, Timestamp timestamp) {
		int result = secKillOrderDetailDao.createNewSecKillOrder(userId, goodsId, buyCount, timestamp);
		return result > 0;
	}
}
