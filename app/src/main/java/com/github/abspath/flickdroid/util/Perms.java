package com.github.abspath.flickdroid.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/1
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Perms {
    public static final String TAG = Perms.class.getSimpleName();

    public static final int PERMISSION_REQUEST = 0;

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static boolean hasAllGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    public static boolean hasAllGranted(Context ctxt) {
        for (String permission : PERMISSIONS) {
            int result = ActivityCompat.checkSelfPermission(ctxt, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    public static void checkPermissions(Activity ctxt) {
        if (hasAllGranted(ctxt)) return;
        ActivityCompat.requestPermissions(ctxt, PERMISSIONS, PERMISSION_REQUEST);
    }
}
