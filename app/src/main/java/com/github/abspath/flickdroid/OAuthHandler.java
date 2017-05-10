package com.github.abspath.flickdroid;

import com.github.abspath.flickdroid.util.Constants;
import com.github.abspath.flickdroid.util.MsgDigests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/16
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class OAuthHandler {

    public static void oauth() {

    }

    private static String generateSignature() throws UnsupportedEncodingException {
        String encodedMethod = urlEncode(Constants.METHOD_PART_BASE_STRING);
        String encodedUrl = urlEncode(Constants.REQUEST_TOKEN_URL);
        String encodedParams = getEncodedParams();
        String baseString = encodedMethod + "&" + encodedUrl + "&" + encodedParams;
        String key = Constants.CONSUMER_SECRET + "&" + "";
        return urlEncode(MsgDigests.hmacSha1(baseString, key));
    }

    private static String getEncodedParams() throws UnsupportedEncodingException {
        Map<String, String> origParams = getOriginalParams();
        Iterator<Map.Entry<String, String>> paramEntries = origParams.entrySet().iterator();
        StringBuilder encodedParams = new StringBuilder();
        while (paramEntries.hasNext()) {
            Map.Entry<String, String> paramEntry = paramEntries.next();
            String encodedKey = urlEncode(paramEntry.getKey());
            String encodedValue = urlEncode(paramEntry.getValue());
            encodedParams.append(encodedKey).append("=").append(encodedValue);
            if (paramEntries.hasNext()) encodedParams.append("&");
        }
        return urlEncode(encodedParams.toString());
    }

    private static Map<String, String> getOriginalParams() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("oauth_callback", Constants.OAUTH_CALLBACK);
        params.put("oauth_consumer_key", Constants.CONSUMER_KEY);
        params.put("oauth_nonce", getNonce());
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", getTimestamp());
        params.put("oauth_version", "1.0");
        return params;
    }

    private static String urlEncode(String input) throws UnsupportedEncodingException {
        return URLEncoder.encode(input, "UTF-8");
    }

    private static String getNonce() {
        return UUID.randomUUID().toString();
    }

    private static String getTimestamp() {
        return String.valueOf(new Date().getTime() / 1000);
    }
}
