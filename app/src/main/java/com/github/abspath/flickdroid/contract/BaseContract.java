package com.github.abspath.flickdroid.contract;

import android.support.annotation.NonNull;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface BaseContract {

    interface BaseIView<P extends BaseIPresenter> {

        void bindPresenter(@NonNull P presenter);
    }

    interface BaseIPresenter<V extends BaseIView> {

        void bindView(@NonNull V view);

        void start();

        void stop();

        void unbindView();
    }
}
