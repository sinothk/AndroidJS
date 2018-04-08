package com.android.js.v1;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.js.lib.JsCallback;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/8/008 on 15:52
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class DemoInterface {
    private WebView mWebView;

    protected Context mContext;
    protected Activity mActivity;

    public DemoInterface(WebView webView) {
        mWebView = webView;
        mContext = mWebView.getContext();
    }

    /**
     * 短暂气泡提醒
     *
     * @param message 提示信息
     */
    @android.webkit.JavascriptInterface
    public void toast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出记录的测试JS层到Java层代码执行损耗时间差
     *
     * @param timeStamp js层执行时的时间戳
     */
    @android.webkit.JavascriptInterface
    public void testLossTime(long timeStamp) {
        timeStamp = System.currentTimeMillis() - timeStamp;
        toast(String.valueOf(timeStamp));
    }

    @android.webkit.JavascriptInterface
    public void delayJsCallBack(final int ms, final String backMsg, final JsCallback jsCallback) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    jsCallback.apply(backMsg);//
                } catch (JsCallback.JsCallbackException je) {
                    je.printStackTrace();
                }
            }
        }, ms);
    }
}
