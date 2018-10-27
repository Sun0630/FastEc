package com.sunxin.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.sunxin.core.app.Globle;

/**
 * @author sunxin
 * @date 2018/10/27 10:05 AM
 * @desc 测量工具类
 */
public class DimenUtil {

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth() {

        Resources resources = Globle.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        return displayMetrics.widthPixels;
    }



    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight() {

        Resources resources = Globle.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        return displayMetrics.heightPixels;
    }


}
