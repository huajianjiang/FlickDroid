package com.github.abspath.flickdroid.net;

import android.support.annotation.UiThread;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface NetCallback<T> {
    @UiThread
    void onPrepare();

    @UiThread
    void onEcho(T data);

    @UiThread
    void onFailure(Exp exp);

    @UiThread
    void onPeace();
}
