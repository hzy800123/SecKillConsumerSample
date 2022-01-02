package com.microvideoSecKill.rocketmq;

public class SecKillMessage {
    private String userId;
    private String goodsId;
    private Integer buyCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "SecKillMessage{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", buyCount=" + buyCount +
                '}';
    }
}
