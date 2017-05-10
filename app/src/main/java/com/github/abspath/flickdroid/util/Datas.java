package com.github.abspath.flickdroid.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/5/6
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Datas {

    public static Map<String, String> of(String... pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("need couple of pairs");
        }
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            String key = pairs[i];
            String value = pairs[++i];
            if (Ifs.isNullOrEmpty(key)) {
                continue;
            }
            map.put(key, value);
        }
        return Collections.unmodifiableMap(map);
    }

}
