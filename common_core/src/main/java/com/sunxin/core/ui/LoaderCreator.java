package com.sunxin.core.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author sunxin
 * @date 2018/10/26 4:10 PM
 * @desc 加载动画创建类
 */
public final class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();


    /**
     * 用一种缓存的方式创建Loader。不用每次使用的时候都去反射，性能会大大提高
     * @param type
     * @param context
     * @return
     */
    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null){
            Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));

        return avLoadingIndicatorView;
    }


    private static Indicator getIndicator(String name){
        if (TextUtils.isEmpty(name)){
            return null;
        }

        final StringBuilder drawableClassName = new StringBuilder();

        if (!name.contains(".")){
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();

            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");

        }

        drawableClassName.append(name);

        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
