package com.hyjz.hnovel.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hyjz.hnovel.R;
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.presenter.RegesterPresenter;
import com.hyjz.hnovel.utils.TimeCount;
import com.hyjz.hnovel.utils.ToastUtil;
import com.hyjz.hnovel.view.RegesterView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;

public class RegesterAc extends BaseActivity<RegesterPresenter> implements RegesterView, View.OnClickListener {
    //    private TextView actionbar_title;
    TimeCount count;
    //昵称
    private EditText et_nickName;
    //手机号
    private EditText et_phone;
    //验证码
    private EditText et_code;
    //获取验证码按钮
    private TextView tv_verifcode;
    //账号的密码
    private EditText et_newpwd;
    //再次确认密码
    private EditText et_pass_word;
    //注册确认按钮
    private TextView tv_next;
    //邀请码输入框
    private EditText et_yaoqing_code;
    private OkHttpClient client;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_regester;
    }

    @Override
    public void initView() {
        initOkHttp();
        et_nickName = findViewById(R.id.et_nickName);
        et_phone = findViewById(R.id.et_phone);
        et_code = findViewById(R.id.et_code);
        tv_verifcode = findViewById(R.id.tv_verifcode);
        et_newpwd = findViewById(R.id.et_newpwd);
        et_pass_word = findViewById(R.id.et_pass_word);
        tv_next = findViewById(R.id.tv_next);
        tv_next.setOnClickListener(this);
        tv_verifcode.setOnClickListener(this);
        et_yaoqing_code = findViewById(R.id.et_yaoqing_code);
    }
    //初始化okhttp
    private void initOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Override
    protected RegesterPresenter createPresenter() {
        return new RegesterPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:  //注册确认
                getCheckAuthCode(et_nickName.getText().toString().trim(),et_phone.getText().toString().trim(), et_code.getText().toString().trim(),et_newpwd.getText().toString().trim(),et_pass_word.getText().toString().trim(),et_yaoqing_code.getText().toString().trim());
//			Intent intent = new Intent(context, ForgotpassAc.class);
//			intent.putExtra("phone",et_phone.getText().toString().trim());
//			startActivity(intent);
                break;
            case R.id.tv_verifcode:  //获取验证码
                getAuthCode(et_phone.getText().toString().trim());
                break;
            default:
                break;
        }
    }
    /**
     * 获取验证码
     *
     * @param phone 手机号码
     *
     */
    public void getAuthCode(final String phone) {

        if(TextUtils.isEmpty(phone)){
            ToastUtil.showShort(mContext,"手机号码不能为空");
            return;
        }
        if(!isMobile(phone)){
            ToastUtil.showShort(mContext,"请输入正确的手机号码");
            return;
        }
        getCode(phone);


//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("mobileNumber", phone);

//        Http(RetrofitSingleton.getInstance().getApiService().getPhoneCode(phone
//                ).map((str)-> GsonUtils.fromJson(str, LoginBean.class)),
//                new Subscriber<BaseBean<String>>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                    @Override
//                    public void onNext(BaseBean<String> b) {
//                        if (b.isSuccess()==true){
//                            ToastUtil.showShort(mContext,"验证码获取成功");
////                            ToastUtil.showShort(mContext,b.getErrorMsg());
//                            count = new TimeCount(60000, 1000, tv_verifcode, et_phone,mContext);
//                            count.start();
//                            et_phone.setFocusable(false);
//                        }
//
//                    }
//                });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what ) {
                case 1:

                ToastUtil.showShort(mContext,"验证码获取成功");
                break;
            }

        }
    };
    //    /请求二维码
    public void  getCode(String result) {
        String url=  "http://hyjz.tpddns.cn:8089/api/user/mobileverification";
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("phoneNumber", result)
                .build();
        Request requestPost = new Request.Builder()
                .url(url)
                .post(requestBodyPost)
                .build();
        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                handler.sendEmptyMessage(1);
//                            ToastUtil.showShort(mContext,b.getErrorMsg());
                            count = new TimeCount(60000, 1000, tv_verifcode, et_phone,mContext);
                            count.start();
                            et_phone.setFocusable(false);
//                BaseBean<QrIdBean> bean = GsonUtils.fromJson(body, QrIdBean.class);
//                if (bean.getResult() != null) {
//                    Message msg = new Message();
//                    msg.arg1 = Math.toIntExact(bean.getResult().getId());
//                    msg.what = 1;
//                    handler.sendMessage(msg);
////                    handler.sendEmptyMessage(1);
//
//                }
//
////                JSONObject obj = null;


            }
        });
    }
    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、170、173、177、180、181、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][345678]\\d{9}";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }
    /**
     * 验证手机号码和短信验证码
     *
     * @param phone
     *            手机号码
     * @param authCode
     *            验证码
     */
    public void getCheckAuthCode(final String nickName,String phone, String authCode,String newPassWord,String confirmPwd,String yaoqing_code) {
        if(TextUtils.isEmpty(nickName)){
            ToastUtil.showShort(mContext,"昵称不能为空");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            ToastUtil.showShort(mContext,"手机号码不能为空");
            return;
        }
        if(!isMobile(phone)){
            ToastUtil.showShort(mContext,"请输入正确的手机号码");
            return;
        }

        if(TextUtils.isEmpty(authCode)){
            ToastUtil.showShort(mContext,"验证码不能为空");
            return;
        }
        if(TextUtils.isEmpty(newPassWord)){
            ToastUtil.showShort(mContext,"密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(confirmPwd)){
            ToastUtil.showShort(mContext,"确认密码不能为空");
            return;
        }
//        if(TextUtils.isEmpty(yaoqing_code)){
//            ToastUtil.showShort(mContext,"邀请码不能为空");
//            return;
//        }
        mPresenter.regester(phone,nickName,authCode,newPassWord);

    }

    @Override
    public void sucess() {
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
