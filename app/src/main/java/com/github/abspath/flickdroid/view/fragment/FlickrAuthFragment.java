package com.github.abspath.flickdroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;

import com.github.abspath.flickdroid.R;
import com.github.abspath.flickdroid.contract.CommentContract;
import com.github.abspath.flickdroid.oauth.OAuthManager;
import com.github.abspath.flickdroid.util.CommonCallback;
import com.github.abspath.flickdroid.util.Constants;
import com.github.abspath.flickdroid.util.Logger;
import com.github.abspath.flickdroid.util.Views;

import static com.github.abspath.flickdroid.R.id.authView;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class FlickrAuthFragment extends BaseFragment<CommentContract.IPresenter>
        implements CommentContract.IView
{
    private static final String TAG = FlickrAuthFragment.class.getSimpleName();

    private WebView mAuthView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpandableListView
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.frag_auth, container, false);
        mAuthView = Views.find(rootView, authView);
        mAuthView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Logger.e(TAG, "onPageFinished=>" + url);
                if (url.startsWith(Constants.OAUTH_CALLBACK)) {

                    String oauthVerifier = url.split("&")[1].split("=")[1];

                    OAuthManager.get()
                            .getAccessToken(oauthVerifier, new CommonCallback.Post<Boolean>() {
                                @Override
                                public void post(Boolean arg) {
                                    if (arg) {
                                        Logger.e(TAG, "onCompleted=>");
                                        //presenter.loadComments();
                                        presenter.testLogin();
                                    }
                                }
                            });
                }
            }
        });

        WebSettings webSettings = mAuthView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        OAuthManager.get().getRequestToken(new CommonCallback.Posts() {
            @Override
            public void post(Object... args) {
                boolean isSuccessful = (boolean) args[0];
                final String authUrl = isSuccessful ? (String) args[1] : "";
                if (isSuccessful) {
                    mAuthView.post(new Runnable() {
                        @Override
                        public void run() {
                            mAuthView.loadUrl(authUrl);
                        }
                    });
                }
            }
        });
    }
}
