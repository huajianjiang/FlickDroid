package com.github.abspath.flickdroid.view.activity;

import android.support.v4.app.Fragment;

import com.github.abspath.flickdroid.view.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected Fragment onCreateFragment() {
        return new MainFragment();
    }
}
