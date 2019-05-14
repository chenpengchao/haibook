package com.hyjz.hnovel.api;

import com.hyjz.hnovel.bean.BookRecommend;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @description: 网络请求的service
 * @date 2017/6/18  19:28
 */

public interface ApiService {

    /**
     * 书架列表api/readlike/like/list/page
     * pageNum: 1
     * pageSize: 10
     *
     * @return
     */

    @POST("/api/readlike/like/list/page")
    Observable<String> getRecomend(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize
    );

    //注册接口
    //@FormUrlEncoded
    @POST("/api/user/reader-register")
    Observable<String> regester(
            @Query("phoneNum") String phoneNum,
            @Query("password") String password,
            @Query("nickName") String nickName,
            @Query("code") String code
    );

    /**
     * 登录接口/api/user/readerlogin
     */
    @POST("/api/user/readerlogin")
    Observable<String> login(
            @Query("phoneNum") String phoneNum,
            @Query("password") String password
    );

    /**
     * 我的界面信息/api/user/reader-center
     */
    @POST("/api/user/reader-center")
    Observable<String> minecenter(
            @Query("access_token") String sessionId
    );

    /**
     * 获取个人信息接口/api/user/info
     */
    @POST("/api/user/info")
    Observable<String> getPersioninfo(
            @Query("access_token") String sessionId
    );

    /**
     * 意见反馈/api/advice/add
     */
    @POST("/api/advice/add")
    Observable<String> postFeed(
            @Query("access_token") String sessionId,
            @Query("advice") String advice,
            @Query("wxAccount") String wxAccount,
            @Query("phoneNum") String phoneNum
    );

    /**
     * 充值书币/api/recharge/info/bookcoin
     */
    @POST("/api/recharge/info/bookcoin")
    Observable<String> rechargeBookCoin(
            @Query("access_token") String sessionId
    );

    /**
     * 我的评论/api/comment/my
     */
    @POST("/api/comment/my")
    Observable<String> myComment(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize
    );

    /**
     * 点击充值，进入书券界面请求接口/api/user/mybookcoin
     */
    @POST("/api/user/mybookcoin")
    Observable<String> myBookTicket(
            @Query("access_token") String sessionId
    );

    /**
     * 书券有效期接口/api/user/coupons/list/valid
     */
    @POST("/api/user/coupons/list/valid")
    Observable<String> myBookTicketVid(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize
    );

    /**
     * 书币充值记录/pay/bill/list/bookcoin
     */
    @POST("/api/pay/bill/list/bookcoin")
    Observable<String> myBookTicketRechargeList(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize,
            @Query("year") Integer year,
            @Query("month") Integer month
    );

    /**
     * 书币消费记录/api/consume/record/list/page
     */
    @POST("/api/consume/record/list/page")
    Observable<String> myBookTicketConsumList(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize,
            @Query("year") Integer year,
            @Query("month") Integer month
    );

    /**
     * 进入钱包页面接口
     * /api/user/mywallet
     */
    @POST("/api/user/mywallet")
    Observable<String> myWallet(
            @Query("access_token") String sessionId
    );

    /**
     * 钱包收益排行
     * /api/user/list/rank/wallet
     */
    @POST("/api/user/list/rank/wallet")
    Observable<String> myWalletPaihang(
            @Query("access_token") String sessionId,
            @Query("pageNum") Integer pageNum,
            @Query("pageSize") Integer pageSize
    );

    /**
     * 金币兑换书币页面
     * //api/exchange/bookcoin
     */
    @POST("api/exchange/info")
    Observable<String> goldtobookcoininfo(
            @Query("access_token") String sessionId,
            @Query("exchangeType") Integer exchangeType
    );

    /**
     * 金币兑换书币
     * //api/exchange/bookcoin
     */
    @POST("/api/exchange/bookcoin")
    Observable<String> goldtobookcoin(
            @Query("access_token") String sessionId,
            @Query("bookcoin") Integer bookcoin
    );

    /**
     * 金币兑换余额api/exchange/money
     */
    @POST("/api/exchange/money")
    Observable<String> goldtomoney(
            @Query("access_token") String sessionId,
            @Query("cash") Integer cash
    );

    /**
     * 提现界面api/user/withdraw
     */
    @POST("/api/user/withdraw")
    Observable<String> withdrawinfo(
            @Query("access_token") String sessionId
    );

    /**
     * 提现api/withdraw/apply/add
     */
    @POST("/api/withdraw/apply/add")
    Observable<String> withdraw(
            @Query("access_token") String sessionId,
            @Query("withdrawAmount") Float withdrawAmount
    );

    /**
     * 成为vip页面信息api/recharge/info/readvip
     */
    @POST("/api/recharge/info/readvip")
    Observable<String> readvip(
            @Query("access_token") String sessionId
    );

    /**
     * 小说详情api/book/detail
     */
    @POST("api/book/detail")
    Observable<String> bookDetail(
            @Query("access_token") String sessionId,
            @Query("bookId") Long bookId
    );

    /**
     * 小说阅读界面api/chapter/detail
     * chapterCoin: 0
     * chapterId: 96063
     */
    @POST("api/book/detail")
    Observable<String> bookReader(
            @Query("access_token") String sessionId,
            @Query("chapterCoin") Integer chapterCoin,
            @Query("chapterId") Long chapterId
    );

}

