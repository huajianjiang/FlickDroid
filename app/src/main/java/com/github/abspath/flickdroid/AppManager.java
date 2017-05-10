package com.github.abspath.flickdroid;

import com.github.abspath.flickdroid.net.FlickrService;
import com.github.abspath.flickdroid.net.NetManager;

/**
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/4/23
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class AppManager {
    private static AppManager INSTANCE;
    private FlickrService mApi;

    private AppManager() {}

    public static AppManager getInstance() {
        synchronized (AppManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new AppManager();
            }
            return INSTANCE;
        }
    }

    public FlickrService getFlickrService() {
        if (mApi == null) {
            mApi = NetManager.getInstance().createService(FlickrService.class);
        }
        return mApi;
    }
}
