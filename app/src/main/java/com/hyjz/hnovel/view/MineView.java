package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.MineBean;

public interface MineView extends BaseView {
    void onSuccess(MineBean bean);

    void onFailure();
}
