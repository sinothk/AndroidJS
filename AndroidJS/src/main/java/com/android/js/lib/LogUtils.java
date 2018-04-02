package com.android.js.lib;

import android.util.Log;

import com.android.js.BuildConfig;


/**
 * Date: 18-02-20
 * Time: 下午4:40
 * Copyright: Copyright (c) 2018
 */

public class LogUtils {

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    public static void safeCheckCrash(String tag, String msg, Throwable tr) {
        if (isDebug()) {
            throw new RuntimeException(tag + " " + msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }
}
