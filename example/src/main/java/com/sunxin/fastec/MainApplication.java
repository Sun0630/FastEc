package com.sunxin.fastec;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.sunxin.core.app.Globle;
import com.sunxin.core.net.rx.AddCookieInterceptor;
import com.sunxin.fastec.event.TestEvent;
import com.sunxin.core.net.interceptors.DebugInterceptor;
import com.sunxin.ec.database.DatabaseManager;
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
                .withWebHost("https://www.baidu.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new EcFontModule())
                .withWxAppId("")
                .withWxAppSecret("")
                .withInterceptor(new AddCookieInterceptor())
                .withInterceptor(new DebugInterceptor("user_profile", R.raw.user_profile))
                .withInterceptor(new DebugInterceptor("index", R.raw.index))
                .withInterceptor(new DebugInterceptor("sort_list", R.raw.sort_list))
                .withInterceptor(new DebugInterceptor("sort_content_list", R.raw.sort_content_data_1))
                .withInterceptor(new DebugInterceptor("shop_cart_list", R.raw.shop_cart))
                .withJavascriptInterface("common")
                .withWebEvent("test",new TestEvent())
                .configure();

        // 初始化数据库
        DatabaseManager.getInstance().init(this);

        //数据库检测工具
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .build()

        );
    }
}
