package com.github.abspath.flickdroid.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class NetObserver<T> implements Observer<Echo<T>> {
    private static final String TAG = NetObserver.class.getSimpleName();

    private NetCallback<T> mCallback;

    public NetObserver(NetCallback<T> callback) {
        this.mCallback = callback;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(Echo<T> echo) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
