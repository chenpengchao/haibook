package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBeanList;
import com.hyjz.hnovel.bean.BookRecommend;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.utils.LogUtils;
import com.hyjz.hnovel.view.BookShelfView;
import com.socks.library.KLog;

import rx.Subscriber;

public class BookShelfPresenter extends BasePresenter<BookShelfView> {


    public BookShelfPresenter(BookShelfView view) {
        super(view);
    }
    public void getRecommed(String sex) {
        addSubscription(mApiService.getRecomend(sex).map((str)->GsonUtils.fromJsonArray(str,BookRecommend.class)),
                new Subscriber<BaseBeanList<BookRecommend>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                KLog.e(e.getLocalizedMessage());
                mView.onError();
            }

            @Override
            public void onNext(BaseBeanList<BookRecommend> response) {
                LogUtils.logd("recommend",response.getResult().toString());
                mView.onShowRecommendData(response.getResult());
            }

        });
    }
}
