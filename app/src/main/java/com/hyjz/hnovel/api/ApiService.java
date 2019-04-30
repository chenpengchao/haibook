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
     * 获取新闻列表
     *
     * @return
     */

    @GET("/book/recommend")
    Observable<String> getRecomend(@Query("gender") String gender);

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
}

