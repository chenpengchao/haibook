package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookDetailBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BookDetailView;

import rx.Subscriber;

public class BookDetailPresenter extends BasePresenter<BookDetailView> {
    public BookDetailPresenter(BookDetailView view) {
        super(view);
    }
    public void getbookdetail(Long bookId) {
        addSubscription(mApiService.bookDetail(MyApp.getInstance().getToken(), bookId).map((str) -> GsonUtils.fromJson(str, BookDetailBean.class)),
                new Subscriber<BaseBean<BookDetailBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<BookDetailBean> b) {
                        mView.success(b.getResult());
                    }
                });
    }
}
