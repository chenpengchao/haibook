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
import com.hyjz.hnovel.base.BaseActivity;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.bean.UserData;
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

import rx.Subscriber;

import static com.ykdr.ykdr.ykdr.utils.GsonUtils.fromJson;

public class ShiftAc extends BaseActivity {
    //标题
    private TextView tv_tittle;
    //返回按钮
    private ImageView back;
    //列表
    private ListView lv_shift;
    private Gson gson;
    private List<UserData> lists = new ArrayList<>();
    private FileOutputStream out;
    private BufferedWriter writer;
    private FileInputStream in;
    private BufferedReader reader;
    private AcountAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shift;
    }

    @Override
    protected void onInitView() throws PackageManager.NameNotFoundException {
        gson = new Gson();
        tv_tittle = findViewById(R.id.actionbar_title);
        tv_tittle.setText("切换账户");
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
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
                        login(user.getAcount(), user.getPasswd());
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
    private void login(String phone, String password) {

        /**
         * {
         "success": true,
         "errorMsg": null,
         "errorCode": null,
         "access_token": "4a2f97b0-0b07-4df9-a993-493934ea9f90",
         "expire_time": 7200
         }
         */
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//        @SuppressLint("MissingPermission")

        @SuppressLint("MissingPermission") String deviceId = tm.getDeviceId();
        Log.e("DEVICE_ID ", deviceId + " ");
        Http(RetrofitSingleton.getInstance().getApiService().Login(phone, password, deviceId,2).map((str) -> GsonUtils.fromJson(str, LoginBean.class)),
                new Subscriber<BaseBean<String>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<String> b) {
                        if (b.isSuccess() == true) {
                            if (b.getIs_black() == 2) {
                                ToastUtil.showShort(mContext, b.getErrorMsg() + "");
                                return;
                            }
                            ToastUtil.showShort(mContext, "登录成功");
//                            stogeUser(b.getHeadImg());
//                            ToastUtil.showShort(mContext,b.getErrorMsg());
//                            SharedPreferencesHelper.remove("token");
                            stogeUser(b.getHeadImg(), phone, password);
                            MyApp.getInstance().setNickName(b.getNickName());
                            MyApp.getInstance().setAgentId(b.getId());
                            MyApp.getInstance().setHeadImage1(b.getHeadImg());
                            MyApp.getInstance().setHeadImageshift(b.getHeadImg());
                            MyApp.getInstance().setRongToken(b.getRcloudToken());
                            MyApp.getInstance().setIsAdmin(b.getIs_admin());
                            MyApp.getInstance().setIsBlack(b.getIs_black());
                            if (b.getIs_vip() != null) {
                                MyApp.getInstance().setIsVip(b.getIs_vip());
                            }

                            MyApp.getInstance().setToken(b.getAccess_token().toString().trim());
//                MyApp.getInstance().setToken("24a54cde-03c0-4545-9656-434a533abfa5");

                            MyApp.getInstance().setUserName(phone);
                            MyApp.getInstance().setPassWord(password);
                            getMineParam();
                            MainActivity.instance.finish();
                            startActivity(new Intent(mContext, MainActivity.class));
                            EventBus.getDefault().post("refreshMinefm","refreshMinefm");
                            finish();

                        }

                    }
                });
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

    private void getMineParam() {
        Http(RetrofitSingleton.getInstance().getApiService().MineParam(MyApp.getInstance().getToken()).map((str) -> fromJson(str, MineBean.class)),
                new Subscriber<BaseBean<MineBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean<MineBean> b) {
                        if (b.isSuccess() == true) {
                            MineBean bean = b.getResult();
                            MyApp.getInstance().setUser(bean);
                        }
                    }
                });
    }

    /**
     * 初始化
     */
    public void initView() {
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
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
    protected void onInitData() {

    }
}
