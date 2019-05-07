package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.GoldtoBookCoinInfoBean;
import com.hyjz.hnovel.bean.GoldtoMoneyInfoBean;

public interface GoldtoMoneyView extends BaseView {
    void onInfoSuccess(GoldtoMoneyInfoBean bean);

    void onGoldToMoneySuccess();
    void onGoldToMoneyFailue();
}
