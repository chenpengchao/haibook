package com.hyjz.hnovel.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class HiMoneyFm extends BaseFragment {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_hi_money_fm;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        back.setVisibility(View.INVISIBLE);
        title.setText("嗨赚");
    }

    @Override
    protected void loadData() {

    }
}
