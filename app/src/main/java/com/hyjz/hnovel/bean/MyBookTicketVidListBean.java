package com.hyjz.hnovel.bean;

import java.util.List;

public class MyBookTicketVidListBean {
    private Integer endRow;//":0,
    private Integer firstPage;//":0,
    private boolean hasNextPage;//":false,
    private boolean hasPreviousPage;//":false,
    private boolean isFirstPage;//":false,
    private boolean isLastPage;//":true,
    private Integer lastPage;//":0,
    private List<TicketVidBean> list;//":[
    private Integer navigateFirstPage;//":0,
    private Integer navigateLastPage;//":0,
    private Integer navigatePages;//":8,
    //            "navigatepageNums":[
//
//            ],
    private Integer nextPage;//":0,
    private String orderBy;//":null,
    private Integer pageNum;//":0,
    private Integer pageSize;//":10,
    private Integer pages;//":0,
    private Integer prePage;//":0,
    private Long size;//":0,
    private Integer startRow;//":0,
    private Integer total;//":0

    public static class TicketVidBean {
        private String note;//":null,
        private Integer totalNum;//":100,
        private Long createTime;//":1555401822000,
        private Integer surplusNum;//":100,
        private Long userCouponId;//":198,
        private Long dueTime;//":1556697822000,
        private Integer validDays;//":7

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

        public Long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Long createTime) {
            this.createTime = createTime;
        }

        public Integer getSurplusNum() {
            return surplusNum;
        }

        public void setSurplusNum(Integer surplusNum) {
            this.surplusNum = surplusNum;
        }

        public Long getUserCouponId() {
            return userCouponId;
        }

        public void setUserCouponId(Long userCouponId) {
            this.userCouponId = userCouponId;
        }

        public Long getDueTime() {
            return dueTime;
        }

        public void setDueTime(Long dueTime) {
            this.dueTime = dueTime;
        }

        public Integer getValidDays() {
            return validDays;
        }

        public void setValidDays(Integer validDays) {
            this.validDays = validDays;
        }
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public List<TicketVidBean> getList() {
        return list;
    }

    public void setList(List<TicketVidBean> list) {
        this.list = list;
    }

    public Integer getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(Integer navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public Integer getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(Integer navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public Integer getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(Integer navigatePages) {
        this.navigatePages = navigatePages;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
