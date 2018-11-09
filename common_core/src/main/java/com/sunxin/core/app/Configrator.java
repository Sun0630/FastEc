package com.sunxin.core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sunxin.core.delegates.web.event.Event;
import com.sunxin.core.delegates.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;

/**
 * @author sunxin
 * @date 2018/10/25 3:13 PM
 * @desc 全局的配置类
 */
public class Configrator {

    private static final HashMap<String, Object> CONFIGS = new HashMap<>();

    /**
     * 字体图标容器
     */
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    /**
     * 拦截器容器
     */
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private static final Handler HANDLER = new Handler();

    private Configrator() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    final HashMap<String, Object> getConfigs() {
        return CONFIGS;
    }

    /**
     * 静态内部类实现单例模式
     */
    private static class Holder {
        private static final Configrator INSTANCE = new Configrator();
    }

    public static Configrator getInsance() {
        return Holder.INSTANCE;
    }

    /**
     * 配置完成
     */
    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }


    /**
     * 初始化字体图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 配置apihost
     *
     * @param apiHost
     * @return
     */
    public final Configrator withApiHost(String apiHost) {
        CONFIGS.put(ConfigType.API_HOST.name(), apiHost);
        return this;
    }


    public final Configrator withWebHost(String webHost) {
        CONFIGS.put(ConfigType.WEB_HOST.name(), webHost);
        return this;
    }

    /**
     * 添加字体
     *
     * @param descriptor
     * @return
     */
    public final Configrator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configrator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        Globle.getConfigrations().put(ConfigType.INTERCEPTORS.name(), INTERCEPTORS);
        return this;
    }


    public final Configrator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        Globle.getConfigrations().put(ConfigType.INTERCEPTORS.name(), INTERCEPTORS);
        return this;
    }

    public final Configrator withJavascriptInterface(@NonNull String name) {
        CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(), name);
        return this;
    }

    public final Configrator withWebEvent(@NonNull String name, @NonNull Event event) {
        EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }


    public final Configrator withWxAppId(String appId) {
        Globle.getConfigrations().put(ConfigType.WX_APP_ID.name(), appId);
        return this;
    }

    public final Configrator withWxAppSecret(String appSecret) {
        Globle.getConfigrations().put(ConfigType.WX_APP_SECRET.name(), appSecret);
        return this;
    }

    public final Configrator withActivity(Activity activity) {
        Globle.getConfigrations().put(ConfigType.ACTIVITY.name(), activity);
        return this;
    }


    /**
     * 检查配置
     */
    public void checkConfigration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configration is not ready!");
        }
    }

    public final <T> T getConfigration(Object key) {
        checkConfigration();
        final Object value = CONFIGS.get(key);
        if (value == null) {
            throw new RuntimeException(key.toString() + "IS NULL");
        }
        return (T) CONFIGS.get(key);
    }

}
