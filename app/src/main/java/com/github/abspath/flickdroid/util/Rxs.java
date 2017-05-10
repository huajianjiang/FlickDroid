package com.github.abspath.flickdroid.util;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Rxs {
    private static final String TAG = Rxs.class.getSimpleName();

    public static <T> Observable<T> applyBase(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //    public static void unSubscribe(Subscription consumer) {
    //        if (consumer != null && !consumer.isUnsubscribed()) {
    //            consumer.unsubscribe();
    //        }
    //    }
}
