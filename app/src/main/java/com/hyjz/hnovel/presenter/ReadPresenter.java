package com.hyjz.hnovel.presenter;

import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.BaseBean;
import com.hyjz.hnovel.bean.ReadBean;
import com.hyjz.hnovel.utils.GsonUtils;
import com.hyjz.hnovel.view.ReadView;

import rx.Subscriber;

public class ReadPresenter  extends BasePresenter<ReadView> {
    public ReadPresenter(ReadView view) {
        super(view);
    }
    public void getChapterInfer(Integer chapterCoin,Long chapterId) {
        addSubscription(mApiService.bookReader(MyApp.getInstance().getToken(), chapterCoin, chapterId).map((str) -> GsonUtils.fromJson(str, ReadBean.class)),
                new Subscriber<BaseBean<ReadBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<ReadBean> bean) {
                        mView.showChapterInfo(bean.getResult());
                    }
                });

    }
}
