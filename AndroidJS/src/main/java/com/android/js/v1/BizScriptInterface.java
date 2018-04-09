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

import com.android.js.lib.JsCallback;

public class BizScriptInterface extends NativeInterface {

    public BizScriptInterface(WebView webView) {
        super(webView);
    }

    @android.webkit.JavascriptInterface
    public void getLoginInfo(final JsCallback jsCallback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final String userInfo = "https://dev.mi.com/console/images/weixin-qr.png"; //"LiangYT";

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            jsCallback.apply(userInfo);
                        } catch (JsCallback.JsCallbackException je) {
                            je.printStackTrace();
                        }
                    }
                }, 0);
            }
        }).start();
    }
}