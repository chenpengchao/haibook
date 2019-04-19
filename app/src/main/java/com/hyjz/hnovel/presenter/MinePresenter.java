package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MineBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.MineView;

import rx.Subscriber;

/**
 *我的页面presenter
 */
public class MinePresenter extends BasePresenter<MineView> {
    public MinePresenter(MineView view) {
        super(view);
    }

    public void getMineData() {
        mView.showLoading("加载中");
        String token = MyApp.getInstance().getToken();
        addSubscription(mApiService.minecenter(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, MineBean.class)),
                new Subscriber<BaseBean<MineBean>>() {

                    @Override
                    public void onCompleted() {
                        mView.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<MineBean> b) {
                        if (b.getMessage().equals("SUCCESS")) {
                            MineBean bean = b.getResult();
                            mView.onSuccess(bean);
                        }
                    }
                });

    }
}
