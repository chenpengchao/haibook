package com.hyjz.hnovel.bean;

import java.util.ArrayList;
import java.util.List;

public class ReadVipBean {
    private Long dueTime;//: 1558155735000
    private List<GoodsListBean> goodsList = new ArrayList<>();//: [,…]

    private String headImg;//: "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKoVn29tXiaGc3tCnovDOk5FXdPkA68iapZDKkm8zCyBXn7Xj8nOdVBxicQSSmWdF4ZtUguvVsGfnwbQ/132"
    private Integer isVip;//: 1
    private String nickName;//: "达人君"
    private Long userId;//: 617
    private String vipDueTime;//: "2019-05-18 13:02:15"
    private Integer vipMonth;//: 1

    public Long getDueTime() {
        return dueTime;
    }

    public void setDueTime(Long dueTime) {
        this.dueTime = dueTime;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVipDueTime() {
        return vipDueTime;
    }

    public void setVipDueTime(String vipDueTime) {
        this.vipDueTime = vipDueTime;
    }

    public Integer getVipMonth() {
        return vipMonth;
    }

    public void setVipMonth(Integer vipMonth) {
        this.vipMonth = vipMonth;
    }

    public static class GoodsListBean{
    private Integer cashAmount;//: 10
    private Integer firstRechargeRewardNum;//: 0
    private Integer goodsNum;//: 1
    private Integer isHot;//: 0
    private String note;//: null
    private Long rechargeId;//: 11
    private Integer rewardNum;//: 0
    private Integer vipDiscount;//: 1

    public Integer getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Integer cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getFirstRechargeRewardNum() {
        return firstRechargeRewardNum;
    }

    public void setFirstRechargeRewardNum(Integer firstRechargeRewardNum) {
        this.firstRechargeRewardNum = firstRechargeRewardNum;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public Integer getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(Integer rewardNum) {
        this.rewardNum = rewardNum;
    }

    public Integer getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(Integer vipDiscount) {
        this.vipDiscount = vipDiscount;
    }
}
}
