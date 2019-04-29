package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookTicketConsumListBean;
import com.hyjz.hnovel.bean.BookTicketRechargeListBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BookTicketConsumCodeView;
import com.hyjz.hnovel.view.BookTicketRechargeCodeView;

import rx.Subscriber;

public class BookTicketConsumeCodePresenter extends BasePresenter<BookTicketConsumCodeView> {
    public BookTicketConsumeCodePresenter(BookTicketConsumCodeView view) {
        super(view);
    }
    public void getBookTicketConsumCodeList(Integer num,Integer year,Integer month) {
        addSubscription(mApiService.myBookTicketConsumList(MyApp.getInstance().getToken(),num,15,year,month)
                .map((str) -> GsonUtils.fromJson(str, BookTicketConsumListBean.class)), new Subscriber<BaseBean<BookTicketConsumListBean>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(BaseBean<BookTicketConsumListBean> b) {
                if (b.getResult() != null) {
                    mView.onSuccess(b.getResult());
                }

            }


        });
    }
}
