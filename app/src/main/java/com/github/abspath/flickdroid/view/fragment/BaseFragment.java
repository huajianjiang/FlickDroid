package com.github.abspath.flickdroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.abspath.flickdroid.contract.BaseContract;
import com.github.abspath.flickdroid.util.Ifs;
import com.github.abspath.flickdroid.util.Views;


/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public abstract class BaseFragment<P extends BaseContract.BaseIPresenter> extends Fragment
        implements BaseContract.BaseIView<P>
{
    private static final String TAG = BaseFragment.class.getSimpleName();

    protected P presenter;

    /**
     * 是否第一次页面加载
     */
    private boolean mFirstVisible = true;

    private View mContentView;
    private View mLoadingView;
    private View mEmptyView;

    @SuppressWarnings("unused")
    public View onBuildView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return null;
    }

    @SuppressWarnings("unused")
    public void onInitView(View root) {
    }

    public void setLoadingView(int resId) {
        setLoadingView(LayoutInflater.from(getContext()).inflate(resId, null));
    }

    public void setEmptyView(int resId) {
        setEmptyView(LayoutInflater.from(getContext()).inflate(resId, null));
    }

    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    @Override
    public void bindPresenter(@NonNull P presenter) {
        Ifs.isNotNull(presenter, "presenter can not be null");
        this.presenter = presenter;
    }

    /**
     * 插入一些全局 UI 界面, eg: loading dialog, data loading error, net error view 等等
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 一个包含插入了全局UI界面的内容视图层级 root View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        mContentView = onBuildView(inflater, container, savedInstanceState);
        View result = mContentView;
        if (mContentView != null) {
            if (mEmptyView != null || mLoadingView != null) {
                if (!(mContentView instanceof FrameLayout)) {
                    result = new FrameLayout(getContext());
                    ((FrameLayout) result)
                            .addView(mContentView, ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT);// 插入内容 View
                }
                FrameLayout rootView = (FrameLayout) result;
                if (mEmptyView != null) {
                    mEmptyView.setVisibility(View.GONE);
                    rootView.addView(mEmptyView, ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);// 插入空 View
                }
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(View.GONE);
                    rootView.addView(mLoadingView, ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);// 插入加载 View
                }
            }
        }
        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) onInitView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFirstVisible && getUserVisibleHint()) {
            presenter.start();
            mFirstVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mFirstVisible && getUserVisibleHint() && isVisible()) {
            presenter.start();
            mFirstVisible = false;
        }
    }

    private void hideContentView() {
        if (Views.isVisible(mContentView)) {
            mContentView.setVisibility(View.GONE);
        }
    }

    private void hideEmptyView() {
        if (Views.isVisible(mEmptyView)) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    private void hideLoadingView() {
        if (Views.isVisible(mLoadingView)) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    private void showContentView() {
        hideLoadingView();
        hideEmptyView();
        if (mContentView != null && Views.isGone(mContentView)) {
            mContentView.setVisibility(View.VISIBLE);
        }
    }

    protected void showLoadingView() {
        hideContentView();
        hideEmptyView();
        if (mLoadingView != null && Views.isGone(mLoadingView)) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    protected void showEmptyView() {
        hideContentView();
        hideLoadingView();
        if (mEmptyView != null && Views.isGone(mEmptyView)) {
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stop();
    }
}
