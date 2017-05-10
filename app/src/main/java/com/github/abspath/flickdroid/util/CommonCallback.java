package com.github.abspath.flickdroid.util;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface CommonCallback {

    interface Get<T> {
        T get();
    }

    interface Post<T> {
        void post(T arg);
    }

    interface Posts {
        void post(Object... args);
    }

}
