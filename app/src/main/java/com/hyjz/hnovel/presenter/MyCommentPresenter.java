package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.MyCommentView;

import rx.Subscriber;

public class MyCommentPresenter extends BasePresenter<MyCommentView> {
    public MyCommentPresenter(MyCommentView view) {
        super(view);
    }
    public void myComment(Integer num) {
        mView.showLoading("加载中...");
        addSubscription(mApiService.myComment(MyApp.getInstance().getToken(),num, 10).map((str) -> GsonUtils.fromJson(str, String.class)),
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
//                            mView.onSuccess();
                        }
                    }
                });
    }
}
