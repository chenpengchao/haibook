package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.ReadBean;

public interface ReadView extends BaseView {
    void showChapterInfo(ReadBean bean);

    void showChapterList();
}
