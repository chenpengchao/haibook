package com.hyjz.hnovel.fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.base.BasePresenter;

import butterknife.Bind;


public class FirstFm extends BaseFragment {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_first_fm;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        back.setVisibility(View.INVISIBLE);
        title.setText("首页");
    }



    @Override
    protected void loadData() {

    }
}
