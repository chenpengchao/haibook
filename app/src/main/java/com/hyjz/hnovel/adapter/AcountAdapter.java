package com.hyjz.hnovel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.bean.UserData;
import com.hyjz.hnovel.utils.GlideUtils;
import com.hyjz.hnovel.view.CallBackClick;
import com.hyjz.hnovel.weight.CircleImageView;

import java.util.List;


/**
 * Created by jobs on 2016/10/2.
 */

public class AcountAdapter extends ArrayAdapter implements View.OnClickListener{
    private int resource;
    private CallBackClick backClick;

    public AcountAdapter(Context context, int textViewResourceId, List<UserData> objects, CallBackClick backClick) {
        super(context, textViewResourceId, objects);
        resource = textViewResourceId;
        this.backClick = backClick;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserData user = (UserData) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, null);
        CircleImageView imgurl = view.findViewById(R.id.iv_find_header);
        TextView muti_acount = (TextView) view.findViewById(R.id.tv_find_name);
        ImageView image_delete = (ImageView) view.findViewById(R.id.tv_add_friend);
        GlideUtils.loadRound(getContext(),user.getHeadurl(),imgurl);
        muti_acount.setText(user.getAcount());
        //设置点击事件
        muti_acount.setOnClickListener(this);
        image_delete.setOnClickListener(this);
        //传递position
        muti_acount.setTag(position);
        image_delete.setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (backClick != null) {
            //调用回调函数
            backClick.onClick(v);
        }
    }
}
