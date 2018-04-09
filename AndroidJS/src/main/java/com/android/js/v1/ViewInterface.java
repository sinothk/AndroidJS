package com.android.js.v1;

import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/8/008 on 17:39
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class ViewInterface {

    private WebView mWebView;

    protected Context mContext;

    public ViewInterface(WebView webView) {
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
}
