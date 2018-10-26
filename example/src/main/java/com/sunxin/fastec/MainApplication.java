package com.sunxin.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.sunxin.core.app.Globle;
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
                .withApiHost("http://xxx")
                .withIcon(new FontAwesomeModule())
                .withIcon(new EcFontModule())
                .configure();
    }
}
