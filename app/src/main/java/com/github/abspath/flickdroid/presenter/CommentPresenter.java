package com.github.abspath.flickdroid.presenter;

import com.github.abspath.flickdroid.contract.CommentContract;
import com.github.abspath.flickdroid.data.Comments;
import com.github.abspath.flickdroid.data.model.A;
import com.github.abspath.flickdroid.net.FlickrRequest;
import com.github.abspath.flickdroid.util.Logger;
import com.github.abspath.flickdroid.util.Rxs;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.github.abspath.flickdroid.util.Constants.USER_COMMENTS_METHOD;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/25
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class CommentPresenter extends NetPresenter<CommentContract.IView>
        implements CommentContract.IPresenter
{
    private static final String TAG = CommentPresenter.class.getSimpleName();

    @Override
    public void start() {
    }

    @Override
    public void loadComments() {
        FlickrRequest request = new FlickrRequest.Builder(USER_COMMENTS_METHOD)
                .params("per_page", "20", "page", "1").build();

        setRequest(request);

        Rxs.applyBase(service.comments(request.params()))
                .subscribe(new Observer<Comments>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Comments echo) {
                        Logger.e(TAG, "onNext>>>" + echo.items.item.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError>>>>" + e);
                    }

                    @Override
                    public void onComplete() {
                        Logger.e(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void testLogin() {
        FlickrRequest request = new FlickrRequest.Builder("flickr.test.login").build();

        setRequest(request);

        Rxs.applyBase(service.testLogin())
                .subscribe(new Observer<A>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(A echo) {
                        Logger.e(TAG, "onNext>>>" + echo.user.username._content);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError>>>>" + e.getCause());
                    }

                    @Override
                    public void onComplete() {
                        Logger.e(TAG, "onComplete");
                    }
                });
    }
}




