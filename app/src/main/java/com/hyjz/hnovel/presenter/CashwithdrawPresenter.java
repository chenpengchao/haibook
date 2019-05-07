package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.WithDrawInfoBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.CashwithdrawView;

import rx.Subscriber;

public class CashwithdrawPresenter extends BasePresenter<CashwithdrawView> {
    public CashwithdrawPresenter(CashwithdrawView view) {
        super(view);
    }
    public void withdrawinfo() {
        mView.showLoading("加载中...");
        addSubscription(mApiService.withdrawinfo(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, WithDrawInfoBean.class)),
                new Subscriber<BaseBean<WithDrawInfoBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<WithDrawInfoBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onInfoSuccess(b.getResult());

                        }
                    }
                });
    }
    public void withdraw(Float cash) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.withdraw(MyApp.getInstance().getToken(),cash).map((str) -> GsonUtils.fromJson(str, String.class)),
                new Subscriber<BaseBean<String>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<String> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onWithDrawSuccess();
                        }
                    }
                });
    }
}
