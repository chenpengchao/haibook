package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.GoldtoBookCoinInfoBean;

public interface GoldToBookCoinView extends BaseView {
    void onInfoSuccess(GoldtoBookCoinInfoBean bean);

    void onGoldToBookCoinSuccess();
    void onGoldToBookCoinFailue();
}
