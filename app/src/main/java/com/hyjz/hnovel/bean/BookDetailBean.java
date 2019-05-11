package com.hyjz.hnovel.bean;

import java.util.ArrayList;
import java.util.List;

public class BookDetailBean {
    private AuthorInfo authorInfo;//: {,…}
    private BookInfo bookInfo;
    public static class BookInfo{
        private Long authorId;//: 173
        private String authorName;//: "一叶"
        private String bookCover;//: "http://kwayxiaoshuo.oss-cn-beijing.aliyuncs.com/fe1585b8-4e12-4e41-94fa-40136c70d9e0"
        private Long bookId;//: 1552
        private String bookName;//: "全能小神医：病毒统统到碗里来"
        private Integer bookStatus;//: 2
        private Integer bookType;//: 1
        private Integer chapterCoin;//: 0
        private Integer chapterFreeNum;//: 20
        private Long clickNum;//: 8561
        private Integer commentNum;//: 0
        private Long lastChapterId;//: 65682
        private String lastChapterTitle;//: "第二百二十四章 大结局"
        private Long likeNum;//: 430
        private Integer likeStatus;//: 0
        private String readHint;//: "继续阅读"
        private Long readingChpaterId;//: 65459
        private String readingChpaterTitle;//: "第一章 病毒回收系统"
        private Long seriesId;//: 48
        private String seriesName;//: "都市异能"
        private Integer shareNum;//: 249
        private String summary;//: "你见过什么都没做，就能治好患者疾病的医生吗？
        //                ↵你见过会帮大歌星写专辑的医生吗？
//                ↵你见过煮菜比五星级酒店大厨还好吃的医生吗？
//                ↵你见过打篮球直接在底线投全场三分球的医生吗？
//                ↵......
//                ↵这么牛逼，只因实习生叶南获得了病毒回收系统，一切从这里开始。"
        private List<String> tags = new ArrayList<>();//: ["都市", "异能", "神医"]
        private String updateTime;//: "更新于20天前"
        private Long wordNum;//: 745813

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

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

        public Integer getBookType() {
            return bookType;
        }

        public void setBookType(Integer bookType) {
            this.bookType = bookType;
        }

        public Integer getChapterCoin() {
            return chapterCoin;
        }

        public void setChapterCoin(Integer chapterCoin) {
            this.chapterCoin = chapterCoin;
        }

        public Integer getChapterFreeNum() {
            return chapterFreeNum;
        }

        public void setChapterFreeNum(Integer chapterFreeNum) {
            this.chapterFreeNum = chapterFreeNum;
        }

        public Long getClickNum() {
            return clickNum;
        }

        public void setClickNum(Long clickNum) {
            this.clickNum = clickNum;
        }

        public Integer getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Integer commentNum) {
            this.commentNum = commentNum;
        }

        public Long getLastChapterId() {
            return lastChapterId;
        }

        public void setLastChapterId(Long lastChapterId) {
            this.lastChapterId = lastChapterId;
        }

        public String getLastChapterTitle() {
            return lastChapterTitle;
        }

        public void setLastChapterTitle(String lastChapterTitle) {
            this.lastChapterTitle = lastChapterTitle;
        }

        public Long getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(Long likeNum) {
            this.likeNum = likeNum;
        }

        public Integer getLikeStatus() {
            return likeStatus;
        }

        public void setLikeStatus(Integer likeStatus) {
            this.likeStatus = likeStatus;
        }

        public String getReadHint() {
            return readHint;
        }

        public void setReadHint(String readHint) {
            this.readHint = readHint;
        }

        public Long getReadingChpaterId() {
            return readingChpaterId;
        }

        public void setReadingChpaterId(Long readingChpaterId) {
            this.readingChpaterId = readingChpaterId;
        }

        public String getReadingChpaterTitle() {
            return readingChpaterTitle;
        }

        public void setReadingChpaterTitle(String readingChpaterTitle) {
            this.readingChpaterTitle = readingChpaterTitle;
        }

        public Long getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(Long seriesId) {
            this.seriesId = seriesId;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
        }

        public Integer getShareNum() {
            return shareNum;
        }

        public void setShareNum(Integer shareNum) {
            this.shareNum = shareNum;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Long getWordNum() {
            return wordNum;
        }

        public void setWordNum(Long wordNum) {
            this.wordNum = wordNum;
        }
    }

    public static class AuthorInfo{
        private Long authorId;//: 173
        private String authorName;//: "一叶"
        private String bookName;//: "全能小神医：病毒统统到碗里来"
        private String headImg;//: "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJNjibPRI
        // 3ZhWdSicvMUsDM0eXsiboxx3jzx3hvkYWicdbficeNRAl4WznFXYAsicxkUoyxvFOp5bHGcm8g/132

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(AuthorInfo authorInfo) {
        this.authorInfo = authorInfo;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }
}
