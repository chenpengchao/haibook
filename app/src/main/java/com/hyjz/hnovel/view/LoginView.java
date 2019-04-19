package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.LoginBean;

public interface LoginView extends BaseView {
    void success(LoginBean bean);

    void error();
}
