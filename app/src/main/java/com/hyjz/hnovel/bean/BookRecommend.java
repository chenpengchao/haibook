package com.hyjz.hnovel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookRecommend {
        public static class BookShelfList{
                private String _id;
                private String authorName;//: "我是琦哥哥、"
                private String bookCover;//: "http://kwayxiaoshuo.oss-cn-beijing.aliyuncs.com/d1dbb38f-3370-4221-acb9-2e095498d3ab"
                public Long bookId;//: 1514
                public String bookName;//: "活人禁区"
                private Integer bookStatus;//: 2
                private boolean hasNew;//: false
                private String lastChapterTitle;//: "新书《阴门诡事》来啦！"
                private Integer likeStaus;//: 1
                private Integer readLikeId;//: 1388
                private String readingChapterOrder;//: "阅读至1章"
                private String updateTime;//: "更新于15天前"
                public boolean isFromSD = false;
                public String getAuthorName() {
                        return authorName;
                }

                public void setAuthorName(String authorName) {
                        this.authorName = authorName;
                }

                public String getBookCover() {
                        return bookCover;
                }

                public void setBookCover(String bookCover) {
                        this.bookCover = bookCover;
                }

                public Long getBookId() {
                        return bookId;
                }

                public void setBookId(Long bookId) {
                        this.bookId = bookId;
                }

                public String getBookName() {
                        return bookName;
                }

                public void setBookName(String bookName) {
                        this.bookName = bookName;
                }

                public Integer getBookStatus() {
                        return bookStatus;
                }

                public void setBookStatus(Integer bookStatus) {
                        this.bookStatus = bookStatus;
                }

                public boolean isHasNew() {
                        return hasNew;
                }

                public void setHasNew(boolean hasNew) {
                        this.hasNew = hasNew;
                }

                public String getLastChapterTitle() {
                        return lastChapterTitle;
                }

                public void setLastChapterTitle(String lastChapterTitle) {
                        this.lastChapterTitle = lastChapterTitle;
                }

                public Integer getLikeStaus() {
                        return likeStaus;
                }

                public void setLikeStaus(Integer likeStaus) {
                        this.likeStaus = likeStaus;
                }

                public Integer getReadLikeId() {
                        return readLikeId;
                }

                public void setReadLikeId(Integer readLikeId) {
                        this.readLikeId = readLikeId;
                }

                public String getReadingChapterOrder() {
                        return readingChapterOrder;
                }

                public void setReadingChapterOrder(String readingChapterOrder) {
                        this.readingChapterOrder = readingChapterOrder;
                }

                public String getUpdateTime() {
                        return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                }

                public boolean isFromSD() {
                        return isFromSD;
                }

                public void setFromSD(boolean fromSD) {
                        isFromSD = fromSD;
                }

                public String get_id() {
                        return _id;
                }

                public void set_id(String _id) {
                        this._id = _id;
                }

                @Override
                public boolean equals(Object obj) {
                        if (obj instanceof BookShelfList) {
                                BookShelfList bean = (BookShelfList) obj;
                                return this._id.equals(bean._id);
                        }
                        return super.equals(obj);
                }

                @Override
                public int hashCode() {
                        return this._id.hashCode();
                }
        }
        private Integer endRow;//: 5
        private Integer firstPage;//: 1
        private boolean hasNextPage;//: false
        private boolean hasPreviousPage;//: false
        private boolean isFirstPage;//: true
        private boolean isLastPage;//: true
        private Integer lastPage;//: 1
        private List<BookShelfList> list = new ArrayList<>();//: [{likeStaus: 1, lastChapterTitle: "新书《阴门诡事》来啦！", bookStatus: 2, hasNew: false,…},…]
        private Integer navigateFirstPage;//: 1
        private Integer navigateLastPage;//: 1
        private Integer navigatePages;//: 8
        private List<Integer> navigatepageNums = new ArrayList<>();//: [1]
        private Integer nextPage;//: 0
        private String orderBy;//: null
        private Integer pageNum;//: 1
        private Integer pageSize;//: 10
        private Integer pages;//: 1
        private Integer prePage;//: 0
        private Integer size;//: 5
        private Integer startRow;//: 1
        private Integer total;//: 5

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

        public List<BookShelfList> getList() {
                return list;
        }

        public void setList(List<BookShelfList> list) {
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
