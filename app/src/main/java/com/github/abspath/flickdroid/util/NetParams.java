package com.github.abspath.flickdroid.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/25
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class NetParams {
    private static NetParams INSTANCE;

    private HashMap<String, String> mArgs;
    private Map<String, String> mParams;

    private NetParams() {
        mArgs = new LinkedHashMap<>();
        mParams = new LinkedHashMap<>();
    }

    public static NetParams get() {
        if (INSTANCE == null) {
            INSTANCE = new NetParams();
        }
        return INSTANCE;
    }

    public Map<String, String> of(String... pairs) {
        mParams.clear();
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("need couple of vars");
        }
        for (int i = 0; i < pairs.length; i++) {
            String key = pairs[i];
            String value = pairs[++i];
            if (Ifs.isNullOrEmpty(key)) {
                continue;
            }
            mParams.put(key, value);
        }
        return mParams;
    }
}
