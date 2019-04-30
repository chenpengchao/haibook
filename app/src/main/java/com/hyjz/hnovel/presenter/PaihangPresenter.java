package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.BookTicketConsumListBean;
import com.hyjz.hnovel.bean.MyWalletPaihangListBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.PaihangView;

import rx.Subscriber;

public class PaihangPresenter extends BasePresenter<PaihangView> {
    public PaihangPresenter(PaihangView view) {
        super(view);
    }
    public void getBookTicketConsumCodeList(Integer num) {
        addSubscription(mApiService.myWalletPaihang(MyApp.getInstance().getToken(),num,15)
                .map((str) -> GsonUtils.fromJson(str, MyWalletPaihangListBean.class)), new Subscriber<BaseBean<MyWalletPaihangListBean>>() {
            @Override
            public void onCompleted() {
                mView.stopLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopLoading();
            }

            @Override
            public void onNext(BaseBean<MyWalletPaihangListBean> b) {
                if (b.getResult() != null) {
                    mView.onSuccess(b.getResult());
                }

            }


        });
    }
}
