package com.github.abspath.flickdroid.contract;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/25
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface CommentContract {

    interface IView extends BaseContract.BaseIView<IPresenter> {

    }

    interface IPresenter extends BaseContract.BaseIPresenter<IView> {
        void loadComments();
        void testLogin();
    }
}
