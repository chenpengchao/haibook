package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
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
    public void getRecommed(Integer mPage) {
        addSubscription(mApiService.getRecomend(MyApp.getInstance().getToken(),mPage,10).map((str)->GsonUtils.fromJson(str,BookRecommend.class)),
                new Subscriber<BaseBean<BookRecommend>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
//                e.printStackTrace();
//                KLog.e(e.getLocalizedMessage());
                mView.stopLoading();
                mView.onError();
            }

            @Override
            public void onNext(BaseBean<BookRecommend> response) {
                mView.onShowRecommendData(response.getResult());
            }

        });
    }
}
