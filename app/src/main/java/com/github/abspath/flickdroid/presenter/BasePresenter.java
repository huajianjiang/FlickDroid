package com.github.abspath.flickdroid.presenter;


import android.support.annotation.NonNull;

import com.github.abspath.flickdroid.contract.BaseContract;
import com.github.abspath.flickdroid.util.Ifs;


/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/27
 * <br>Email: developer.huajianjiang@gmail.com
 */
public abstract class BasePresenter<V extends BaseContract.BaseIView>
        implements BaseContract.BaseIPresenter<V>
{
    private static final String TAG = BasePresenter.class.getSimpleName();
    protected V view;

    @Override
    public void bindView(@NonNull V view) {
        Ifs.isNotNull(view, "view can not be null");
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    protected boolean isActive() {
        return view != null;
    }
}
