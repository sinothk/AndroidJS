package com.android.js.v1;

import android.webkit.WebView;

import com.android.js.lib.JsCallback;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/8/008 on 17:39
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class NativeInterface extends DemoInterface{

    public NativeInterface(WebView webView) {
        super(webView);
    }

    /**
     *
     * @param jsCallback
     */
    @android.webkit.JavascriptInterface
    public void setString(String str, final JsCallback jsCallback) {
        try {
            jsCallback.apply(str);
        } catch (JsCallback.JsCallbackException je) {
            je.printStackTrace();
        }
    }

    /**
     *
     * @param jsCallback
     */
    @android.webkit.JavascriptInterface
    public void getString(final JsCallback jsCallback) {
        try {
            jsCallback.apply("http://www.sinothk.com");
        } catch (JsCallback.JsCallbackException je) {
            je.printStackTrace();
        }
    }

    /**
     *
     * @param jsCallback
     */
    @android.webkit.JavascriptInterface
    public void doCamera(final JsCallback jsCallback) {
        try {
            jsCallback.apply("http://www.sinothk.com");
        } catch (JsCallback.JsCallbackException je) {
            je.printStackTrace();
        }
    }
}
