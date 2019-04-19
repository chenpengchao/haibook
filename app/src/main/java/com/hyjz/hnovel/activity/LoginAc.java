package com.hyjz.hnovel.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.LoginBean;
import com.hyjz.hnovel.bean.MessageEvent;
import com.hyjz.hnovel.bean.UserData;
import com.hyjz.hnovel.presenter.LoginPresenter;
import com.hyjz.hnovel.utils.ToastUtil;
import com.hyjz.hnovel.view.LoginView;
import com.tbruyelle.rxpermissions.RxPermissions;

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


/**
 * 登录界面
 */
public class LoginAc extends BaseActivity<LoginPresenter> implements LoginView, View.OnClickListener {
    String phone = null;
    String password = null;
    private EditText et_phone_number;
    private EditText et_pass_word;
    private View mProgressView;
    private View mLoginFormView;
    Button btn_login;
    private TextView tv_forgetpwd;
    private ImageView img_checkbox;
    private boolean isChecked;
    int loginCount = 1;
    private LinearLayout ll_checkpwd;
    //立即注册
    private TextView tv_zhuce;
    //存储联系人相关
    private List<UserData> lists;
    private Gson gson;
    private FileOutputStream out;
    private BufferedWriter writer;
    private FileInputStream in;
    private BufferedReader reader;
    //    private String acount;
//    private String passwd;
    private String writephone;
    private String writePasswd;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tv_forgetpwd = findViewById(R.id.tv_forgetpwd);
        img_checkbox = findViewById(R.id.img_checkbox);
        ll_checkpwd = findViewById(R.id.ll_checkpwd);
        tv_zhuce = findViewById(R.id.tv_zhuce);
        tv_zhuce.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);
        ll_checkpwd.setOnClickListener(this);
        isChecked = true;
        initview();
//        //先看下有没有以前保存的密码
//        String data = openFile();
//        if (!TextUtils.isEmpty(data) && !data.equals("[]")) {
//            lists = gson.fromJson(data, new TypeToken<List<UserData>>() {
//            }.getType());
//            writephone = lists.get(0).getAcount();
//            writePasswd = lists.get(0).getPasswd();
////            edit_acount.setText(writeAcount);
////            edit_passwd.setText(writePasswd);
////            remenber.setChecked(true);
//        }
        et_pass_word = (EditText) findViewById(R.id.et_pass_word);
        et_phone_number = findViewById(R.id.et_phone_number);
        btn_login = (Button) findViewById(R.id.btn_login);
//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
//        if (SharedPreferencesHelper.getBoolean("isCheck")) {// 判断是否存在记住手机号和密码
//            if (SharedPreferencesHelper.contain("phone") && SharedPreferencesHelper.contain("password")) {
//                phone = SharedPreferencesHelper.getString("phone");
//                password = SharedPreferencesHelper.getString("password");
//                et_phone_number.setText(phone);
//                et_pass_word.setText(password);
//                img_checkbox.setImageResource(R.mipmap.select);
////				img_checkbox.setImageResource(R.drawable.login_selected);
//                isChecked = true;
//                loginCount++;
//            }
//        } else {
//            if (SharedPreferencesHelper.contain("phone")) {
//                phone = SharedPreferencesHelper.getString("phone");
//                et_phone_number.setText(phone);
//                img_checkbox.setImageResource(R.mipmap.notselect);
////				img_checkbox.setImageResource(R.drawable.login_no_selected);
//            }
//        }
//        //如果用户名和密码在本地存储的有的话就自动填写
//        if (MyApp.getInstance().getUserName() != null) {
//            et_phone_number.setText(MyApp.getInstance().getUserName());
//        }
//        if (MyApp.getInstance().getPassWord() != null) {
//            et_pass_word.setText(MyApp.getInstance().getPassWord());
//        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    /**
     * 初始化
     */
    public void initview() {
        lists = new ArrayList<UserData>();
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
        phone = password = writephone = writePasswd = "";
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

    private void attemptLogin() {
//        // Reset errors.
//        mEmailView.setError(null);
//        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        phone = et_phone_number.getText().toString().trim();
        password = et_pass_word.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShort(mContext, "请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(mContext, "请输入验证码");
            return;
        }
        RxPermissions.getInstance(mContext).
                request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
//                        login();
                        mPresenter.login(phone, password);
                    } else {
//                        ToastUtil.showShort(mContext, "缺少权限！");
                        Toast.makeText(mContext, "您没有授权该权限，请在设置中打开授权", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    /**
     * 进行密码的存储
     *
     * @param headImg
     */
    public void stogeUser(String headImg) {
        //判断用户是否输入了新的账号和密码
        if (!phone.equals("") && !writephone.equals(phone) && !lists.contains(new UserData(phone, password, headImg))) {
            lists.add(new UserData(phone, password, headImg));
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_checkpwd:  //记住密码
//                loginCount++;
//                if (loginCount % 2 == 0) {
//                    img_checkbox.setImageResource(R.mipmap.select);
//                    isChecked = true;
//                    SharedPreferencesHelper.saveBoolean("isCheck", true);// 记住手机号码和密码
//                    SharedPreferencesHelper.saveString("phone", phone);//保存手机号
//                    SharedPreferencesHelper.saveString("password", password);//保存密码
//                } else {
//                    img_checkbox.setImageResource(R.mipmap.notselect);
//                    isChecked = false;
//                    SharedPreferencesHelper.saveBoolean("isCheck", false);// 清空记住密码的信息
//                    SharedPreferencesHelper.saveString("phone", phone);
//                    SharedPreferencesHelper.saveString("password", "");
//                }
                break;
            case R.id.tv_forgetpwd:
//                Intent intent = new Intent(mContext, RetrievePasswordAc.class);
//                startActivity(intent);
                break;
            case R.id.tv_zhuce:
//                ToastUtil.showShort(mContext,"App注册暂未开放，请联系您的推荐人通过邀请链接注册加入，谢谢合作");
                Intent intent1 = new Intent(mContext, RegesterAc.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void success(LoginBean b) {

        ToastUtil.showShort(mContext,"登录成功");
        MyApp.getInstance().setPwd(password);
        MyApp.getInstance().setPhoneNum(phone);
        MyApp.getInstance().setNickName(b.getNickName());
        MyApp.getInstance().setUserId(b.getUserId());
        if (b.getHeadImg() != null) {
            MyApp.getInstance().setHeadImg(b.getHeadImg());
        }
        MyApp.getInstance().setToken(b.getSessionId().toString().trim());
        EventBus.getDefault().post(new MessageEvent("login_success"));
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
