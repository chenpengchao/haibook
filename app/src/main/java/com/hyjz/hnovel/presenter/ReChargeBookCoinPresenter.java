package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.RechargeBookCoinView;

import rx.Subscriber;

public class ReChargeBookCoinPresenter extends BasePresenter<RechargeBookCoinView> {
    public ReChargeBookCoinPresenter(RechargeBookCoinView view) {
        super(view);
    }
    public void rechargeBookCoin() {
        addSubscription(mApiService.rechargeBookCoin(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, RechargeBookCoinBean.class)),
                new Subscriber<BaseBean<RechargeBookCoinBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<RechargeBookCoinBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onSuccess(b.getResult());
                        }
                    }
                });
    }
}
