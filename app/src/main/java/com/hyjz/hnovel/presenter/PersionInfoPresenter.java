package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.MyInfoBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.PersionInfoView;

import rx.Subscriber;

public class PersionInfoPresenter  extends BasePresenter<PersionInfoView> {
    public PersionInfoPresenter(PersionInfoView view) {
        super(view);
    }
    public void getInfo() {
        addSubscription(mApiService.getPersioninfo(MyApp.getInstance().getToken()).map((str) -> GsonUtils.fromJson(str, MyInfoBean.class))
                , new Subscriber<BaseBean<MyInfoBean>>(){
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<MyInfoBean> myInfoBeanBaseBean) {
                        mView.onSucess(myInfoBeanBaseBean.getResult());
                    }

                }
        );
    }
}
