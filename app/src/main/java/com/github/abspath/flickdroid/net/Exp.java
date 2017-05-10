package com.github.abspath.flickdroid.net;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/2/24
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Exp extends RuntimeException {
    private static final String TAG = Exp.class.getSimpleName();
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误码对应的错误消息
     */
    private String msg;

    public Exp(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
