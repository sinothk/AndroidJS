package com.sinothk.js.v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.js.lib.JsCallback;
import com.sinothk.js.nativeApi.camera.CameraApi;

import java.io.File;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/9/009 on 10:38
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class WebFrameBaseActivity extends Activity {
    static JsCallback callback;

    protected static WebFrameBaseActivity instance;

    public static WebFrameBaseActivity getInstance(JsCallback jsCallback) {
        callback = jsCallback;
        return instance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "onActivityResult ...................");

        if (requestCode == CameraApi.PHOTO_REQUEST_CAREMA) {
            // 拍照返回部分
            File pictureFile = CameraApi.getPictureFile();
            if (pictureFile != null && callback != null) {
                try {
                    callback.apply("图片地址：" + pictureFile.getAbsolutePath());
//                    callback.apply(pictureFile);
                } catch (JsCallback.JsCallbackException je) {
                    je.printStackTrace();
                }
            } else {
                if (pictureFile == null) {
                    Log.e("onActivityResult", "pictureFile == null ...................");
                } else if (callback == null) {
                    Log.e("onActivityResult", "callback == null...................");
                } else {
                    Log.e("onActivityResult", "未知...................");
                }
            }
        }


    }
}
