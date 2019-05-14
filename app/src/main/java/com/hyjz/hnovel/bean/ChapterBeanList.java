package com.hyjz.hnovel.bean;

import java.util.List;

public class ChapterBeanList {
    public static class ChapterBean{
        private Integer chapterCoin;//: 0
        private Long chapterId;//: 96053
        private Integer chapterOrder;//: 1
        private String chapterTitle;//: "第1章青田遗书"
        private boolean isReaded;//: true
        private String updateTime;//: "2019-01-22 20:35:18"

        public Integer getChapterCoin() {
            return chapterCoin;
        }

        public void setChapterCoin(Integer chapterCoin) {
            this.chapterCoin = chapterCoin;
        }

        public Long getChapterId() {
            return chapterId;
        }

        public void setChapterId(Long chapterId) {
            this.chapterId = chapterId;
        }

        public Integer getChapterOrder() {
            return chapterOrder;
        }

        public void setChapterOrder(Integer chapterOrder) {
            this.chapterOrder = chapterOrder;
        }

        public String getChapterTitle() {
            return chapterTitle;
        }

        public void setChapterTitle(String chapterTitle) {
            this.chapterTitle = chapterTitle;
        }

        public boolean isReaded() {
            return isReaded;
        }

        public void setReaded(boolean readed) {
            isReaded = readed;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }

    private Integer endRow;//: 30
    private Integer firstPage;//: 1
    private boolean hasNextPage;//: true
    private boolean hasPreviousPage;//: false
    private boolean isFirstPage;//: true
    private boolean isLastPage;//: false
    private Integer lastPage;//: 8
    private List<ChapterBean> list;//: [{chapterCoin: 0, chapterOrder: 1, chapterId: 96053, updateTime: "2019-01-22 20:35:18",…}
    private Integer navigateFirstPage;//: 1
    private Integer navigateLastPage;//: 8
    private Integer navigatePages;//: 8
    private List<Integer> navigatepageNums;//: [1, 2, 3, 4, 5, 6, 7, 8]
    private Integer nextPage;//: 2
    private Integer orderBy;//: null
    private Integer pageNum;//: 1
    private Integer pageSize;//: 30
    private Integer pages;//: 50
    private Integer prePage;//: 0
    private Integer size;//: 30
    private Integer startRow;//: 1
    private Integer total;//: 1498

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

    public List<ChapterBean> getList() {
        return list;
    }

    public void setList(List<ChapterBean> list) {
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

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
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
