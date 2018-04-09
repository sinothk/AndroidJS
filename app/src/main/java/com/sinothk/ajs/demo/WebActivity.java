package com.sinothk.ajs.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.js.lib.LogUtils;
import com.android.js.lib.SafeWebView;
import com.sinothk.js.v2.BizScriptInterface;
import com.sinothk.js.v2.WebFrameBaseActivity;

public class WebActivity extends WebFrameBaseActivity {
    public static final String HTML = "file:///android_asset/test.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new InnerWebView(this);
        setContentView(webView);

        webView.loadUrl(HTML);
    }

    protected class InnerWebView extends SafeWebView {

        public InnerWebView(Context context) {
            super(context);

            if (LogUtils.isDebug()) {
                trySetWebDebuggEnabled();
            }
            fixedAccessibilityInjectorException();
            WebSettings ws = getSettings();
            ws.setJavaScriptEnabled(true);
            addJavascriptInterface(new BizScriptInterface(this), "Android");
            setWebChromeClient(new InnerWebChromeClient());
            setWebViewClient(new InnerWebViewClient());
        }

        public class InnerWebChromeClient extends SafeWebChromeClient {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress); // 务必放在方法体的第一行执行；
                // to do your work
                // ...
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // to do your work
                // ...
                return super.onJsPrompt(view, url, message, defaultValue, result); // 务必放在方法体的最后一行执行，或者用if判断也行；
            }
        }

        private class InnerWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(UnsafeWebActivity.HTML)) {
                    view.getContext().startActivity(new Intent(view.getContext(), UnsafeWebActivity.class));
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }
}