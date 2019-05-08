package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.ReadVipBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.BeVipView;

import rx.Subscriber;

public class BeVipPresenter extends BasePresenter<BeVipView> {
    public BeVipPresenter(BeVipView view) {
        super(view);
    }
    public void  showVipInfo() {
        addSubscription(mApiService.readvip(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, ReadVipBean.class)),
                new Subscriber<BaseBean<ReadVipBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<ReadVipBean> bean) {
                        mView.onReadVipInfo(bean.getResult());
                    }
                });
    }
}
