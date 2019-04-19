package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.FeedBackView;

import rx.Subscriber;

public class FeedBackPresenter extends BasePresenter<FeedBackView> {
    public FeedBackPresenter(FeedBackView view) {
        super(view);
    }
    public void postFeedBack(String advice,String wxAccount,String phoneNum) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.postFeed(MyApp.getInstance().getToken(),advice, wxAccount, phoneNum).map((str) -> GsonUtils.fromJson(str, String.class)),
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
                            mView.onSuccess();
                        }
                    }
                });
    }

}
