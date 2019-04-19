package com.hyjz.hnovel.bean;

public class LoginBean {

    /**
     * automaticDeduction : 0
     * gender : null
     * headImg : null
     * lastDoTime : 1553150888
     * nickName : 111
     * phoneNum : 15538092569
     * sessionId : 83ce0547-5ce5-42c5-aa23-4c9fc71cf422
     * userId : 565
     * wxOpenId : null
     */

    private int automaticDeduction;
    private String gender;
    private String headImg;
    private int lastDoTime;
    private String nickName;
    private String phoneNum;
    private String sessionId;
    private Long userId;
    private String wxOpenId;

    public int getAutomaticDeduction() {
        return automaticDeduction;
    }

    public void setAutomaticDeduction(int automaticDeduction) {
        this.automaticDeduction = automaticDeduction;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getLastDoTime() {
        return lastDoTime;
    }

    public void setLastDoTime(int lastDoTime) {
        this.lastDoTime = lastDoTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Object getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
