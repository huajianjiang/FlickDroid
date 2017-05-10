package com.github.abspath.flickdroid.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.abspath.flickdroid.R;
import com.github.abspath.flickdroid.util.Views;
import com.github.abspath.flickdroid.view.activity.FlickrAuthActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MainFragment.class.getSimpleName();
    private Button mOauth;
    private TextView mResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mOauth = Views.find(root, R.id.oauth);
        mOauth.setOnClickListener(this);
        mResult = Views.find(root, R.id.result);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent authIntent = new Intent(getContext(), FlickrAuthActivity.class);
        startActivity(authIntent);
    }
}
