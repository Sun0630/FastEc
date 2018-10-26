package com.sunxin.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

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

    private Configrator() {
        CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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




    /**
     * 检查配置
     */
    public void checkConfigration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configration is not ready!");
        }
    }

    final <T> T getConfigration(Enum<ConfigType> key) {
        checkConfigration();
        return (T) CONFIGS.get(key.name());
    }

}
