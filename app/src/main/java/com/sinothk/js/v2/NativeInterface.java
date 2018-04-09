package com.sinothk.js.v2;

import android.webkit.WebView;

import com.android.js.lib.JsCallback;
import com.sinothk.js.nativeApi.camera.CameraApi;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/8/008 on 17:39
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class NativeInterface extends ViewInterface {

    public NativeInterface(WebView webView) {
        super(webView);
    }

    /**
     * 打开摄像头
     *
     * @param jsCallback
     */
    @android.webkit.JavascriptInterface
    public void openCamera(final JsCallback jsCallback) {
        CameraApi.openCamera(jsCallback);
    }
}
