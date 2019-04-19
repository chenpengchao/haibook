package com.hyjz.hnovel.bean;

import java.util.List;

public class RechargeBookCoinBean {
    private Integer bookCoupon;//": null,
    private List<RechageBookCoinItem> goodsList;//": [
    private Integer isFirstRecharge;//": 0,
    private Integer bookCoin;//": 0

    public Integer getBookCoupon() {
        return bookCoupon;
    }

    public void setBookCoupon(Integer bookCoupon) {
        this.bookCoupon = bookCoupon;
    }

    public List<RechageBookCoinItem> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<RechageBookCoinItem> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getIsFirstRecharge() {
        return isFirstRecharge;
    }

    public void setIsFirstRecharge(Integer isFirstRecharge) {
        this.isFirstRecharge = isFirstRecharge;
    }

    public Integer getBookCoin() {
        return bookCoin;
    }

    public void setBookCoin(Integer bookCoin) {
        this.bookCoin = bookCoin;
    }

    public static class RechageBookCoinItem{
        private String note;//": "备注备注",
        private Integer vipDiscount;//": 1,
        private Integer firstRechargeRewardNum;//": 200,
        private Integer rewardNum;//": 100,
        private Float cashAmount;//": 0.01,
        private Integer goodsNum;//": 1000,
        private Integer isHot;//": 0,
        private Long rechargeId;//": 1

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getVipDiscount() {
            return vipDiscount;
        }

        public void setVipDiscount(Integer vipDiscount) {
            this.vipDiscount = vipDiscount;
        }

        public Integer getFirstRechargeRewardNum() {
            return firstRechargeRewardNum;
        }

        public void setFirstRechargeRewardNum(Integer firstRechargeRewardNum) {
            this.firstRechargeRewardNum = firstRechargeRewardNum;
        }

        public Integer getRewardNum() {
            return rewardNum;
        }

        public void setRewardNum(Integer rewardNum) {
            this.rewardNum = rewardNum;
        }

        public Float getCashAmount() {
            return cashAmount;
        }

        public void setCashAmount(Float cashAmount) {
            this.cashAmount = cashAmount;
        }

        public Integer getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Integer goodsNum) {
            this.goodsNum = goodsNum;
        }

        public Integer getIsHot() {
            return isHot;
        }

        public void setIsHot(Integer isHot) {
            this.isHot = isHot;
        }

        public Long getRechargeId() {
            return rechargeId;
        }

        public void setRechargeId(Long rechargeId) {
            this.rechargeId = rechargeId;
        }
    }


}
