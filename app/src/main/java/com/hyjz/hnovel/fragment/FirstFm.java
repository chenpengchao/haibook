package com.hyjz.hnovel.fragment;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hyjz.hnovel.BuildConfig;
import com.hyjz.hnovel.MainActivity;
import com.hyjz.hnovel.R;
import com.hyjz.hnovel.activity.ShowImgActivity;
import com.hyjz.hnovel.app.MyApp;
import com.hyjz.hnovel.base.BaseFragment;
import com.hyjz.hnovel.base.BasePresenter;
import com.hyjz.hnovel.constant.JsApi;
import com.hyjz.hnovel.constant.MyWebViewClient2;
import com.hyjz.hnovel.utils.DownPicUtil;
import com.hyjz.hnovel.utils.ItemLongClickedPopWindow;
import com.hyjz.hnovel.utils.SizeUtil;
import com.hyjz.hnovel.weight.SlowlyProgressBar;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import butterknife.Bind;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

import static android.app.Activity.RESULT_OK;


public class FirstFm extends BaseFragment {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;

//    private String mUrl="http://www.haishuwu.com/" ;
    private String mUrl=" http://192.168.31.213:8085/" ;
    //    private Context mContext;
    public static DWebView mWebView;
    // 长按查看图片
    private ItemLongClickedPopWindow itemLongClickedPopWindow;
    // 手指触发屏幕的坐标
    private int downX, downY;
    // 需要保存图片的路径
    private String saveImgUrl = "";
    //    private ImageView back;
//    private TextView tv_title;
//    private ImageView iv_right;
//    private TextView tv_left;
    //评论内容
    String content;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private String mCurrentPhotoPath;
    private String mLastPhothPath;
    private ValueCallback<Uri> uploadMessage;
    private Thread mThread;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private SlowlyProgressBar slowlyProgressBar;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_first_fm;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView(View v) {
        super.initView(v);
        back.setVisibility(View.INVISIBLE);
        title.setText("首页");
//        iv_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mWebView.reload();
//            }
//        });
        initview(v);
    }



    @Override
    protected void loadData() {

    }


    private String uploadUrl = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initview(View v) {
//        mUrl = mContext.getIntent().getStringExtra("url");



//        mContext = this;
        slowlyProgressBar =
                new SlowlyProgressBar
                        (
                                (ProgressBar)v. findViewById(R.id.ProgressBar)
                        );

        mWebView = (DWebView) v.findViewById(R.id.webview);
        DWebView.setWebContentsDebuggingEnabled(true);
        mWebView.addJavascriptObject(new JsApi(), null);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用js
        webSettings.setBlockNetworkImage(false); // 解决图片不显示
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        // 设置允许访问文件数据
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true);

//        mWebView.setWebViewClient(new NoAdWebViewClient(mContext,mUrl));
        mWebView.setWebViewClient(new MyWebViewClient2(mContext,slowlyProgressBar));
//        mWebView.setWebViewClient(new MyWebViewClient1(mContext));
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                slowlyProgressBar.onProgressChange(newProgress);
                if (newProgress >= 100) {
                    mWebView.getSettings().setBlockNetworkImage(false);
                    slowlyProgressBar.destroy();
                }
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }
        });
//        mWebView.callHandler("bookInfo", new OnReturnValue<JSONObject>() {
//            @Override
//            public void onValue(JSONObject o) {
//                if (o != null) {
//
////
//                } else {
//
//                }
//
//
//
//            }
//        });
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
////                mProgressBar.setProgress(newProgress);
////                if (newProgress == 100) {
////                    mProgressBar.setVisibility(View.GONE);
////                }
//
//            }
//
//            //For Android  >= 5.0
//            @Override
//            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//
//                uploadMessageAboveL = filePathCallback;
//                uploadPicture();
//                return true;
//            }
//
//
//            //For Android  >= 4.1
//            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
//                uploadMessage = valueCallback;
//                uploadPicture();
//            }
//
//        });
        mWebView.setOnTouchListener(listener);

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult result = ((WebView)v).getHitTestResult();
                if (null == result)
                    return false;
                int type = result.getType();
                if (type == WebView.HitTestResult.UNKNOWN_TYPE)
                    return false;
                if (type == WebView.HitTestResult.EDIT_TEXT_TYPE) {

                }

                itemLongClickedPopWindow = new ItemLongClickedPopWindow(mContext,
                        ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW,
                        SizeUtil.dp2px(mContext, 120), SizeUtil.dp2px(mContext, 90));

                switch (type) {
                    case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                        break;
                    case WebView.HitTestResult.EDIT_TEXT_TYPE:

                        break;
                    case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                        break;
                    case WebView.HitTestResult.GEO_TYPE: // TODO
                        break;
                    case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                        break;
                    case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                        break;
                    case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                        // 获取图片的路径
                        saveImgUrl = result.getExtra();
                        //通过GestureDetector获取按下的位置，来定位PopWindow显示的位置
                        itemLongClickedPopWindow.showAtLocation(v, Gravity.TOP|Gravity.LEFT, downX, downY + 10);
                        break;
                    default:
                        break;
                }

                itemLongClickedPopWindow.getView(R.id.item_longclicked_viewImage)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                itemLongClickedPopWindow.dismiss();
                                Intent intent = new Intent(mContext, ShowImgActivity.class);
                                intent.putExtra("info", saveImgUrl);
                                startActivity(intent);
                            }
                        });
                itemLongClickedPopWindow.getView(R.id.item_longclicked_saveImage)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                itemLongClickedPopWindow.dismiss();
//                                new SaveImage().execute(); // Android 4.0以后要使用线程来访问网络
                                String url = result.getExtra();
                                // 下载图片到本地
                                DownPicUtil.downPic(url, new DownPicUtil.DownFinishListener(){

                                    @Override
                                    public void getDownPath(String s) {
//                                        Toast.makeText(mContext,"保存成功",Toast.LENGTH_LONG).show();
                                        Message msg = Message.obtain();
                                        msg.obj=s;
                                        handler.sendMessage(msg);
                                    }
                                });

                            }
                        });
                return true;
            }
        });
        syncCookie(mContext,mUrl);
        // 加载页面
        mWebView.loadUrl(mUrl);
    }
    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }
    public static boolean clickBack(int keycode, KeyEvent event){
        if(keycode==KeyEvent.KEYCODE_BACK&&mWebView.canGoBack()){
            mWebView.goBack();
        }else if (keycode==KeyEvent.KEYCODE_BACK&&!mWebView.canGoBack()){
            MainActivity.instance.finish();
//            mWebView.goBack();
        }
        return true;
    }
    private void syncCookie(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
//        cookieManager.removeSessionCookie();// 移除旧的[可以省略]
        cookieManager.setCookie(url, "access_token=" + MyApp.getInstance().getToken()+";device=android");
//        cookieManager.setCookie(url,"password="+getMD5Bit32(MyApp.getInstance().getPassWord()));

//        for (int i = 0; i < cookies.size(); i++) {
//            HttpCookie cookie = cookies.get(i);
//            String value = cookie.getName() + "=" + cookie.getValue();
//            cookieManager.setCookie(url, value);
//        }
        CookieSyncManager.getInstance().sync();// To get instant sync instead of waiting for the timer to trigger, the host can call this.
    }
    /**
     * 对字符串md5加密 32位
     *
     * @param sourceStr
     * @return
     */
    public static String getMD5Bit32(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


    View.OnTouchListener listener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            downX = (int) arg1.getX();
            downY = (int) arg1.getY();
            return false;
        }
    };
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length-1];
            try {
                MediaStore.Images.Media.insertImage(mContext.getApplicationContext().getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            mContext.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));
            Toast.makeText(mContext,"图片保存图库成功",Toast.LENGTH_LONG).show();
        }
    };



    /***
     * 功能：用线程保存图片
     *
     * @author wangyp
     */
    private class SaveImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();
                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }
                int idx = saveImgUrl.lastIndexOf(".");
                String ext = saveImgUrl.substring(idx);
                file = new File(sdcard + "/Download/" + new Date().getTime() + ext);
                InputStream inputStream = null;
                URL url = new URL(saveImgUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20000);
                if (conn.getResponseCode() == 200) {
                    inputStream = conn.getInputStream();
                }
                byte[] buffer = new byte[4096];
                int len = 0;
                FileOutputStream outStream = new FileOutputStream(file);
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                result = "图片已保存至：" + file.getAbsolutePath();

                Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                final Uri localUri = Uri.fromFile(file);
                localIntent.setData(localUri);
                mContext.sendBroadcast(localIntent);
            } catch (Exception e) {
                result = "保存失败！" + e.getLocalizedMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        } else {
//            this.finish();
//        }
//        return true;
//    }
    /**
     * 选择相机或者相册
     */
    public void uploadPicture() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请选择图片上传方式");

        //取消对话框
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                //一定要返回null,否则<input type='file'>
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;

                }
            }
        });


        builder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if(!TextUtils.isEmpty(mLastPhothPath)){
                    //上一张拍照的图片删除
                    mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            File file = new File(mLastPhothPath);
                            if(file!= null){
                                file.delete();
                            }
//                            mHandler.sendEmptyMessage(1);

                        }
                    });

                    mThread.start();


                }else{

                    //请求拍照权限
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        takePhoto();
                    } else {
//                        ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                    }
                }







            }
        });
        builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                chooseAlbumPic();


            }
        });

        builder.create().show();

    }

    /**
     * 拍照
     */
    private void takePhoto() {

        StringBuilder fileName = new StringBuilder();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileName.append(UUID.randomUUID()).append("_upload.png");
        File tempFile = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mCurrentPhotoPath = tempFile.getAbsolutePath();
//        startActivityForResult(intent, REQUEST_CODE_CAMERA);


    }

    /**
     * 选择相册照片
     */
    private void chooseAlbumPic() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
//        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE_ALBUM);

    }
}
