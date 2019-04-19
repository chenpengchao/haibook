package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.MyInfoBean;

public interface PersionInfoView extends BaseView {
    void onSucess(MyInfoBean bean);

    void failue();
}
