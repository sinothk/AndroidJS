package com.sinothk.js.nativeApi.camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.android.js.lib.JsCallback;
import com.sinothk.js.v2.WebFrameBaseActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/9/009 on 9:46
 *  项目: AndroidJSLib
 *  描述:
 *  更新:
 * <pre>
 */
public class CameraApi {
    private static Uri imageUri;
    public static File pictureFile;

    /**
     * @param jsCallback
     */
    @android.webkit.JavascriptInterface
    public static void openCamera(final JsCallback jsCallback) {

        openCamera(WebFrameBaseActivity.getInstance(jsCallback));

//        try {
//            jsCallback.apply("http://www.sinothk.com");
//        } catch (JsCallback.JsCallbackException je) {
//            je.printStackTrace();
//        }
    }

    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int CROP_PHOTO = 2;

    public static void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {

            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());

            pictureFile = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(pictureFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, pictureFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(activity, "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    /*
    * 判断sdcard是否被挂载
    */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static File getPictureFile() {
        return pictureFile;
    }
}
