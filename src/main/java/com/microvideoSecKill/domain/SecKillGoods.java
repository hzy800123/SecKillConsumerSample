package com.microvideoSecKill.domain;

import java.util.Date;

public class SecKillGoods {
	private Long id;
	private String secKillGoodsId;
	private Integer stockCount;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSecKillGoodsId() {
		return secKillGoodsId;
	}

	public void setSecKillGoodsId(String secKillGoodsId) {
		this.secKillGoodsId = secKillGoodsId;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
