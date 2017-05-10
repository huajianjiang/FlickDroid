package com.github.abspath.flickdroid.view.activity;

import android.support.v4.app.Fragment;

import com.github.abspath.flickdroid.presenter.CommentPresenter;
import com.github.abspath.flickdroid.view.fragment.FlickrAuthFragment;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class FlickrAuthActivity extends BaseActivity {

    @Override
    protected Fragment onCreateFragment() {
        FlickrAuthFragment v = new FlickrAuthFragment();
        CommentPresenter p = new CommentPresenter();
        v.bindPresenter(p);
        p.bindView(v);
        return v;
    }
}
