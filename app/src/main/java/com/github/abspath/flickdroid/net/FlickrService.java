package com.github.abspath.flickdroid.net;

import com.github.abspath.flickdroid.data.Comments;
import com.github.abspath.flickdroid.data.model.A;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public interface FlickrService {
    /**
     *
     * @param url
     * @param queryMap
     * @param <T>
     * @return
     */
    @GET()
    <T> Observable<Echo<T>> get(@Url String url, @QueryMap Map<String, String> queryMap);

    /**
     * POST 表单形式提交数据 CONTENT_TYPE="application/x-www-form-urlencoded"
     * @param url
     * @param fieldMap
     * @param <T>
     * @return
     */
    @FormUrlEncoded
    @POST()
    <T> Observable<Echo<T>> post(@Url String url, @FieldMap Map<String, String> fieldMap);


    @GET(" ")
    Observable<Comments> comments(@QueryMap Map<String, String> params);

    @GET(" ")
    Observable<A> testLogin();

}
