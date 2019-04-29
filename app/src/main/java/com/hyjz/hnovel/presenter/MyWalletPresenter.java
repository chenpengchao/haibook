package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyWalletBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.MyWalletView;

import rx.Subscriber;

public class MyWalletPresenter extends BasePresenter<MyWalletView> {
    public MyWalletPresenter(MyWalletView view) {
        super(view);
    }
    public void getMyWallet() {
        addSubscription(mApiService.myWallet(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, MyWalletBean.class)),
                new Subscriber<BaseBean<MyWalletBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<MyWalletBean> bean) {

                    }
                });
    }
}
