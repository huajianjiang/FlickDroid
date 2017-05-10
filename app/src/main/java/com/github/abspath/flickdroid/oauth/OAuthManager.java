package com.github.abspath.flickdroid.oauth;

import android.support.annotation.NonNull;

import com.github.abspath.flickdroid.util.CommonCallback;
import com.github.abspath.flickdroid.util.Constants;
import com.github.abspath.flickdroid.util.Ifs;
import com.github.abspath.flickdroid.util.Logger;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.httpclient.okhttp.OkHttpHttpClient;
import com.github.scribejava.httpclient.okhttp.OkHttpHttpClientConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

import static com.github.abspath.flickdroid.util.Constants.OAUTH_CALLBACK;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class OAuthManager {
    private static final OAuthManager INSTANCE = new OAuthManager();
    private static final String TAG = OAuthManager.class.getSimpleName();

    private OAuth10aService mOAuthService;
    private OAuth1RequestToken mRequestToken;
    private OAuth1AccessToken mAccessToken;

    private OAuthManager() {
        mOAuthService = new ServiceBuilder().apiKey(Constants.CONSUMER_KEY)
                .apiSecret(Constants.CONSUMER_SECRET).callback(OAUTH_CALLBACK)
                .httpClient(new OkHttpHttpClient(OkHttpHttpClientConfig.defaultConfig()))
                .debugStream(new DebugStream()).build(FlickrApi.instance());
    }

    public static OAuthManager get() {
        return INSTANCE;
    }

    // oauth step one
    public void getRequestToken(final CommonCallback.Posts callback)
    {
        mOAuthService.getRequestTokenAsync(new OAuthAsyncRequestCallback<OAuth1RequestToken>() {
            @Override
            public void onCompleted(OAuth1RequestToken response) {
                mRequestToken = response;
                String authUrl = mOAuthService.getAuthorizationUrl(response);
                callback.post(true, authUrl);
            }

            @Override
            public void onThrowable(Throwable t) {
                callback.post(false);
            }
        });
    }

    // oauth step three
    public void getAccessToken(String oauthVerifier, final CommonCallback.Post<Boolean> callback) {
        mOAuthService.getAccessTokenAsync(mRequestToken, oauthVerifier,
                new OAuthAsyncRequestCallback<OAuth1AccessToken>() {
                    @Override
                    public void onCompleted(OAuth1AccessToken response) {
                        mAccessToken = response;
                        callback.post(true);
                    }

                    @Override
                    public void onThrowable(Throwable t) {
                        callback.post(false);
                    }
                });
    }

    public Map<String, String> signRequest(String method) {
        return signRequest(Verb.GET, method);
    }

    public Map<String, String> signRequest(Verb verb, String method) {
        return signRequest(verb, method, null);
    }

    public Map<String, String> signRequest(String method, Map<String, String> extraParams) {
        return signRequest(Verb.GET, method, extraParams);
    }

    public Map<String, String> signRequest(Verb verb,String method, Map<String, String> extraParams) {
        final OAuthRequest request = new OAuthRequest(verb, Constants.BASE_URL);
        request.addQuerystringParameter("method", method);
        request.addQuerystringParameter("format", "json");
        request.addQuerystringParameter("nojsoncallback", "1");
        if (!Ifs.isNullOrEmpty(extraParams)) {
            Set<Map.Entry<String, String>> params = extraParams.entrySet();
            for (Map.Entry<String, String> param : params) {
                request.addQuerystringParameter(param.getKey(), param.getValue());
            }
        }
        mOAuthService.signRequest(mAccessToken, request);
        Map<String, String> headers = request.getHeaders();

        Logger.e(TAG, "size=" + headers.size() + "\n" + headers.toString());
        Logger.e(TAG, "url=>" + request.getCompleteUrl());

        return headers;
    }

    private class DebugStream extends ByteArrayOutputStream {
        @Override
        public void write(@NonNull byte[] b) throws IOException {
            super.write(b);
            Logger.e(TAG, new String(b, Charset.forName("UTF-8")));
        }
    }
}
