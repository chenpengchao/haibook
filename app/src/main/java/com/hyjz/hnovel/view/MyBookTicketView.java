package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.MyBookTicketBean;

public interface MyBookTicketView extends BaseView {
    void onGetTicketDataSuccess(MyBookTicketBean bean);
}
