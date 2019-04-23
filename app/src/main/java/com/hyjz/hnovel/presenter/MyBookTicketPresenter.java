package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyBookTicketBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.MyBookTicketView;

import rx.Subscriber;

public class MyBookTicketPresenter extends BasePresenter<MyBookTicketView> {

    public MyBookTicketPresenter(MyBookTicketView view) {
        super(view);
    }
    public void getBookTicketInfo() {
        addSubscription(mApiService.myBookTicket(MyApp.getInstance().getToken())
                .map((str) -> GsonUtils.fromJson(str, MyBookTicketBean.class)), new Subscriber<BaseBean<MyBookTicketBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean<MyBookTicketBean> b) {
                if (b.getResult() != null) {
                    mView.onGetTicketDataSuccess(b.getResult());
                }

            }


        });
    }
}
