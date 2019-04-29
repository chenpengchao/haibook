package com.hyjz.hnovel.bean;

public class BookTicketConsumBean {
    private Long consumeId;//":2788,
    private String note;//":"订阅 女总裁的私人保镖 第十六章 葬礼",
    private String consumTime;//":"3月18日 15:14",
    private Integer bookCoin;//":0

    public Long getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Long consumeId) {
        this.consumeId = consumeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getConsumTime() {
        return consumTime;
    }

    public void setConsumTime(String consumTime) {
        this.consumTime = consumTime;
    }

    public Integer getBookCoin() {
        return bookCoin;
    }

    public void setBookCoin(Integer bookCoin) {
        this.bookCoin = bookCoin;
    }
}
