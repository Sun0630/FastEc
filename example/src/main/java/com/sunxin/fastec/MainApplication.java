package com.sunxin.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.sunxin.core.app.Globle;
import com.sunxin.core.net.interceptors.DebugInterceptor;
import com.sunxin.ec.icon.EcFontModule;

/**
 * @author sunxin
 * @date 2018/10/25 3:52 PM
 * @desc
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Globle
                .init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new EcFontModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
