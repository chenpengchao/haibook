package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyBookTicketBean;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BookTicketVidView;

import rx.Subscriber;

public class BookTicketVidPresenter extends BasePresenter<BookTicketVidView> {
    public BookTicketVidPresenter(BookTicketVidView view) {
        super(view);
    }
    public void getBookTicketVidList(Integer num) {
        addSubscription(mApiService.myBookTicketVid(MyApp.getInstance().getToken(),num,10)
                .map((str) -> GsonUtils.fromJson(str, MyBookTicketBean.class)), new Subscriber<BaseBean<MyBookTicketVidListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean<MyBookTicketVidListBean> b) {
                if (b.getResult() != null) {
                    mView.onBookTicketVidSuccess();
                }

            }


        });
    }

}
