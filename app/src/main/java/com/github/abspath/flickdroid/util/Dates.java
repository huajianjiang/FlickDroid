package com.github.abspath.flickdroid.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/24
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class Dates {
    private static final String TAG = Dates.class.getSimpleName();
    private static Dates INSTANCE;
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    private SimpleDateFormat mDateFormat;

    @SuppressLint("SimpleDateFormat")
    private Dates() {
        mDateFormat = new SimpleDateFormat();
    }

    public static Dates get() {
        if (INSTANCE == null) {
            INSTANCE = new Dates();
        }
        return INSTANCE;
    }

    public Dates applyPattern(String pattern) {
        mDateFormat.applyPattern(pattern);
        return this;
    }

    public String fromDate() {
        return fromDate(new Date());
    }

    public String fromDate(Date date) {
        return mDateFormat.format(date);
    }

    public Date toDate(String date) {
        try {
            return mDateFormat.parse(date);
        } catch (ParseException e) {
            Logger.e(TAG, e.getMessage());
        }
        return null;
    }

    public static long getCurrTimeMillisecs() {
        return new Date().getTime();
    }

    public static String getCurrTimeMillisecsStr() {
        return String.valueOf(new Date().getTime());
    }

    public static long getCurrTimeSecs() {
        return getCurrTimeMillisecs() / 1000;
    }

    public static String getCurrTimeSecsStr() {
        return String.valueOf(getCurrTimeSecs());
    }

}
