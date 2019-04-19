package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.RechargeBookCoinBean;

public interface RechargeBookCoinView extends BaseView {
    void onSuccess(RechargeBookCoinBean bean);

    void onFailue();

}
