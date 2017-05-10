package com.github.abspath.flickdroid.net;

import android.support.annotation.NonNull;

import com.github.abspath.flickdroid.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class NetManager {
    private static NetManager INSTANCE;
    private Retrofit mRetrofit;
    private ParamInterceptor mHeaderIntercept;
    private ParamInterceptor mQueryStringIntercept;

    private NetManager() {
        setupRetrofit(setupOKHttpClient());
    }

    public static NetManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetManager();
        }
        return INSTANCE;
    }

    private OkHttpClient setupOKHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getHeaderParamInterceptor())
                .addInterceptor(getQueryStringParamInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    @NonNull
    public ParamInterceptor getHeaderParamInterceptor() {
        if (mHeaderIntercept == null) {
            mHeaderIntercept = new ParamInterceptor(ParamInterceptor.InterceptType.HEADER);
        }
        return mHeaderIntercept;
    }

    @NonNull
    public ParamInterceptor getQueryStringParamInterceptor() {
        if (mQueryStringIntercept == null) {
            Map<String, String> params = new HashMap<>();
            params.put("format", "json");
            params.put("nojsoncallback", "1");//为了剔除返回的 json 格式数据中的 "jsonFlickrApi" 前缀
            mQueryStringIntercept = new ParamInterceptor(
                    ParamInterceptor.InterceptType.QUERY_STRING, params);
        }
        return mQueryStringIntercept;
    }

    private void setupRetrofit(OkHttpClient okHttpClient) {
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(setupGson()))
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    private Gson setupGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T createService(Class<T> clazzOfService) {
        return mRetrofit.create(clazzOfService);
    }
}
