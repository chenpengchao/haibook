package com.hyjz.hnovel.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.adapter.AcountAdapter;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.LoginBean;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.MineBean;
import com.hyjz.hnovel.bean.UserData;
import com.hyjz.hnovel.presenter.LoginPresenter;
import com.hyjz.hnovel.view.CallBackClick;
import com.hyjz.hnovel.view.LoginView;
import com.hyjz.hnovel.view.MineView;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ShiftAc extends BaseActivity<LoginPresenter> implements LoginView {
    //标题
    @Bind(R.id.title)
    TextView title;
    //返回按钮
    @Bind(R.id.back)
    ImageView back;
    //列表
    private ListView lv_shift;
    private Gson gson;
    private List<UserData> lists = new ArrayList<>();
    private FileOutputStream out;
    private BufferedWriter writer;
    private FileInputStream in;
    private BufferedReader reader;
    private AcountAdapter adapter;
    UserData user = null;
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shift;
    }

    @Override
    public void initView() {
        gson = new Gson();
        title.setText("切换账户");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        lv_shift = findViewById(R.id.lv_shift);
        String data = openFile();
        if (!TextUtils.isEmpty(data) && !data.equals("[]")) {
            lists = gson.fromJson(data, new TypeToken<List<UserData>>() {
            }.getType());
//            writeAcount = lists.get(0).getAcount();
//            writePasswd = lists.get(0).getPasswd();
//            edit_acount.setText(writeAcount);
//            edit_passwd.setText(writePasswd);
//            remenber.setChecked(true);
        }
        adapter = new AcountAdapter(mContext, R.layout.simple_item, lists, new CallBackClick() {
            @Override
            public void onClick(View v) {
                int index = (int) v.getTag();
                UserData user = lists.get((Integer) v.getTag());
                switch (v.getId()) {
                    case R.id.tv_find_name:
//                        writeAcount = user.getAcount();
//                        writePasswd = user.getPasswd();
//                        edit_acount.setText(writeAcount);
//                        edit_passwd.setText(writePasswd);
                        mPresenter.login(user.getAcount(), user.getPasswd());
//                        login(user.getAcount(), user.getPasswd());

                        break;
                    case R.id.tv_add_friend:
                        lists.remove(index);
                        //进行实时存储
                        nowStoge();
                        //通知适配器，数据发生改变了
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
        });
        lv_shift.setAdapter(adapter);
    }
    /**
     * 进行密码的存储
     *
     * @param phone
     * @param headImg
     */
    public void stogeUser(String password, String phone, String headImg) {
        //判断用户是否输入了新的账号和密码
        if (!lists.contains(new UserData(phone, password,headImg))) {
            lists.add(new UserData(phone, password,headImg));
            String data = gson.toJson(lists);
            try {
                //先进行数据清空
                out = openFileOutput("user", MODE_PRIVATE);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                writer.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化
     */
    public void init() {
        gson = new Gson();
        try {
            //以防止没有创建时读取错误
            out = openFileOutput("user", Context.MODE_APPEND);
            in = openFileInput("user");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer = new BufferedWriter(new OutputStreamWriter(out));
        reader = new BufferedReader(new InputStreamReader(in));
        try {
            out.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        acount = passwd = writeAcount = writePasswd = "";
    }


    /**
     * 读取文件
     */
    public String openFile() {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    /**
     * 将缓存在lists中的数据进行存储
     */
    public void nowStoge() {
        try {
            out = openFileOutput("user", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer = new BufferedWriter(new OutputStreamWriter(out));
        String data = gson.toJson(lists);
        try {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void success(LoginBean bean) {
        stogeUser(bean.getHeadImg(), user.getAcount(), user.getPasswd());
        MyApp.getInstance().setPwd(user.getPasswd());
        MyApp.getInstance().setPhoneNum(user.getAcount());
        MyApp.getInstance().setNickName(bean.getNickName());
        MyApp.getInstance().setUserId(bean.getUserId());
        if (bean.getHeadImg() != null) {
            MyApp.getInstance().setHeadImg(bean.getHeadImg());
        }
        MyApp.getInstance().setToken(bean.getSessionId().toString().trim());
        EventBus.getDefault().post(new MessageEvent("refreshMinefm"));
        finish();
    }

    @Override
    public void error() {

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
}
