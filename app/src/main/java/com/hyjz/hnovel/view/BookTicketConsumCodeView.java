package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.BookTicketConsumListBean;
import com.hyjz.hnovel.bean.BookTicketRechargeListBean;

public interface BookTicketConsumCodeView extends BaseView {
    void onSuccess(BookTicketConsumListBean bean);
}
