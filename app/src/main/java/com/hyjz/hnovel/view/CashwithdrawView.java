package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.WithDrawInfoBean;

/**
 * 提现第二个界面view
 */
public interface CashwithdrawView extends BaseView {
    void onInfoSuccess(WithDrawInfoBean bean);
    void onWithDrawSuccess();
}
