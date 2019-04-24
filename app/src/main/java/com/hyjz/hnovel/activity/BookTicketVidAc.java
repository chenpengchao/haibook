package com.hyjz.hnovel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.BookTicketVidPresenter;
import com.hyjz.hnovel.view.BookTicketVidView;

import butterknife.Bind;

/**
 * 书券有效期页面
 */
public class BookTicketVidAc extends BaseActivity<BookTicketVidPresenter> implements BookTicketVidView {
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回按钮
    @Bind(R.id.back)
    ImageView back;


    @Override
    public void initView() {
        title.setText("书券有效期");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter.getBookTicketVidList(1);
    }

    @Override
    protected BookTicketVidPresenter createPresenter() {
        return new BookTicketVidPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_book_ticket_vid;
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void onBookTicketVidSuccess() {

    }
}
