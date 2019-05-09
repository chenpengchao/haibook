package com.hyjz.hnovel.view;

import com.hyjz.hnovel.base.BaseView;
import com.hyjz.hnovel.bean.BookRecommend;

import java.util.List;

public interface BookShelfView extends BaseView {
    void onShowRecommendData(BookRecommend data);
    void onError();
}
