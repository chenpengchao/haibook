package com.hyjz.hnovel.presenter;

import android.net.Uri;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.GoldtoBookCoinInfoBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.GoldToBookCoinView;

import rx.Subscriber;

public class GoldToBookCoinPresenter extends BasePresenter<GoldToBookCoinView> {
    public GoldToBookCoinPresenter(GoldToBookCoinView view) {
        super(view);
    }
    public void goldtobookcoininfo(Integer exchangeType) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.goldtobookcoininfo(MyApp.getInstance().getToken(),exchangeType).map((str) -> GsonUtils.fromJson(str, GoldtoBookCoinInfoBean.class)),
                new Subscriber<BaseBean<GoldtoBookCoinInfoBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorTip(e.getMessage()+"");
                    }

                    @Override
                    public void onNext(BaseBean<GoldtoBookCoinInfoBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            mView.onInfoSuccess(b.getResult());

                        }
                    }
                });
    }
    public void goldtobookcoin(Integer goldcoin) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.goldtobookcoin(MyApp.getInstance().getToken(),goldcoin).map((str) -> GsonUtils.fromJson(str, String.class)),
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
                            mView.onGoldToBookCoinSuccess();
                        } else if (b.getMessage().equals("金币不足")) {
                            mView.onGoldToBookCoinFailue();
                        }
                    }
                });
    }


}
