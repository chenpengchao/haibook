package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookTicketRechargeListBean;
import com.hyjz.hnovel.bean.MyBookTicketVidListBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BookTicketRechargeCodeView;

import rx.Subscriber;

public class BookTicketRechargeCodePresenter  extends BasePresenter<BookTicketRechargeCodeView> {
    public BookTicketRechargeCodePresenter(BookTicketRechargeCodeView view) {
        super(view);
    }
    public void getBookTicketRechargeCodeList(Integer num,Integer year,Integer month) {
        addSubscription(mApiService.myBookTicketRechargeList(MyApp.getInstance().getToken(),num,15,2019,4)
                .map((str) -> GsonUtils.fromJson(str, BookTicketRechargeListBean.class)), new Subscriber<BaseBean<BookTicketRechargeListBean>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(BaseBean<BookTicketRechargeListBean> b) {
                if (b.getResult() != null) {
                    mView.onSuccess(b.getResult());
                }

            }


        });
    }
}
