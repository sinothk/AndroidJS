/**
 * Summary: js脚本所能执行的函数空间
 * Version 1.0
 * Date: 18-02-20
 * Time: 下午4:40
 * Copyright: Copyright (c) 2018
 */

package com.android.js.v1;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.js.lib.JsCallback;


public class JavaScriptInterface {
    private WebView mWebView;

    public JavaScriptInterface(WebView webView) {
        mWebView = webView;
    }

    /**
     * 短暂气泡提醒
     *
     * @param message 提示信息
     */
    @android.webkit.JavascriptInterface
    public void toast(String message) {
        Toast.makeText(mWebView.getContext(), message, Toast.LENGTH_SHORT).show();
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
                    jsCallback.apply(backMsg);
                } catch (JsCallback.JsCallbackException je) {
                    je.printStackTrace();
                }
            }
        }, ms);
    }
}