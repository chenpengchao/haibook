package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.api.ApiService;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.RegesterView;

import rx.Subscriber;

public class RegesterPresenter extends BasePresenter<RegesterView> {
    public RegesterPresenter(RegesterView view) {
        super(view);
    }
    public void regester(String phoneNum,String nickName,String code,String passWord) {
        addSubscription(mApiService.regester(phoneNum, passWord, nickName, code).map((str) -> GsonUtils.fromJson(str, String.class))
                , new Subscriber<BaseBean<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<String> o) {
                        mView.sucess();
                    }
                });
    }

}
