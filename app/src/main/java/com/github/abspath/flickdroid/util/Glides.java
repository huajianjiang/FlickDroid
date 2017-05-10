package com.github.abspath.flickdroid.util;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Title:
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/17
 * <br>Email: developer.huajianjiang@gmail.com
 */

public class Glides {

    public static void loadFromRes(@DrawableRes int resId, @NonNull ImageView iv) {
        Glide.with(iv.getContext()).load(resId).asBitmap().dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    public static void loadformUrl(@NonNull String url, @NonNull ImageView iv) {
        Glide.with(iv.getContext()).load(url).asBitmap()
                //             .placeholder(R.drawable.img_holder)
                //             .error(R.drawable.img_error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(iv);
    }
}
