package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.MyWalletBean;

public interface MyWalletView extends BaseView {
    void onSuccess(MyWalletBean bean);

    void onFailue();
}
