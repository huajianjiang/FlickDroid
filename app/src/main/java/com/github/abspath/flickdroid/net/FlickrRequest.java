package com.github.abspath.flickdroid.net;

import com.github.abspath.flickdroid.util.Datas;

import java.util.Map;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/5/6
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class FlickrRequest {
    private final boolean requireAuth;
    private final String method;
    private final Map<String, String> params;

    private FlickrRequest(Builder builder) {
        this.requireAuth = builder.requireAuth;
        this.method = builder.method;
        this.params = builder.params;
    }

    public boolean requireAuth() {
        return requireAuth;
    }

    public String method() {
        return method;
    }

    public Map<String, String> params() {
        return params;
    }

    public static class Builder {
        private boolean requireAuth = true;
        private String method;
        private Map<String, String> params;

        public Builder(String method) {
            this.method = method;
        }

        public Builder auth(boolean requireAuth) {
            this.requireAuth = requireAuth;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder params(String... paramPairs) {
            this.params = Datas.of(paramPairs);
            return this;
        }

        public FlickrRequest build() {
            return new FlickrRequest(this);
        }
    }

}
