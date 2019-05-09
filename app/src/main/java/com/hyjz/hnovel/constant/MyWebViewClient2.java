package com.hyjz.hnovel.constant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hyjz.hnovel.weight.SlowlyProgressBar;

import java.net.URISyntaxException;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MyWebViewClient2 extends WebViewClient {
    private Context context;
    private SlowlyProgressBar slowlyProgressBar;

    public MyWebViewClient2(Context context, SlowlyProgressBar slowlyProgressBar) {
        this.context = context;
        this.slowlyProgressBar = slowlyProgressBar;
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.getSettings().setJavaScriptEnabled(true);
        super.onPageFinished(view, url);
//        view.loadUrl("javascript:java_obj.showSource(document.documentElement.outerHTML);");
        addImageClickListener(view);//待网页加载完全后设置图片点击的监听方法
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        view.getSettings().setJavaScriptEnabled(true);
        slowlyProgressBar.onProgressStart();
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);

    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//        super.onReceivedSslError(view, handler, error);
//        handler.proceed();
        if (error.getPrimaryError() == android.net.http.SslError.SSL_INVALID) {// 校验过程遇到了bug
            handler.proceed();
        } else {
            handler.cancel();
        }

    }

    private void addImageClickListener(WebView webView) {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistener.openImage(this.src);  " +//通过js代码找到标签为img的代码块，设置点击的监听方法与本地的openImage方法进行连接
                "    }  " +
                "}" +
                "})()");
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {


        WebView.HitTestResult hit = view.getHitTestResult();
        if (hit != null) {
            int hitType = hit.getType();
            if (hitType == WebView.HitTestResult.SRC_ANCHOR_TYPE
                    || hitType == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {// 点击超链接
                if (url.contains("yongka")) {
                    view.loadUrl(url);
                } else if (url.equals("https://yongkajun.com/hkj/")) {
                    view.loadUrl(url);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }

            } else {
                if (url.startsWith("mailto://") || url.startsWith("tel://")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                    return true;
                } else if (url.startsWith("https://wx.tenpay.com")) {
                    return false;
                } else if (url.startsWith("weixin://") || url.startsWith("alipays://")) {
                    Intent intent;
                    try {
                        intent = Intent.parseUri(url,
                                Intent.URI_INTENT_SCHEME);
                        intent.addCategory("android.intent.category.BROWSABLE");
                        intent.setComponent(null);
                        // intent.setSelector(null);
                        context.startActivity(intent);
                    } catch (URISyntaxException e) {
                        return false;
//                        e.printStackTrace();
                    }

                } else if (url.startsWith("https://") || url.startsWith("http://")) {
                    view.loadUrl(url);
//                    view.stopLoading();
                    return true;
                } else {
                    view.loadUrl(url);
                }

            }
        } else {
            view.loadUrl(url);
        }
        return false;

    }



}
