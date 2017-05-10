package com.github.abspath.flickdroid.util;

import android.app.Application;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/6
 * <br>Email: developer.huajianjiang@gmail.com
 */
@SuppressWarnings("unused")
public class App extends Application {
    public static final String TAG = App.class.getSimpleName();
    private static App INSTANCE;

    public App() {
        super();
        INSTANCE = this;
    }

    public static App getInstance() {
        return INSTANCE;
    }
}
