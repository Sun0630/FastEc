package com.sunxin.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * @author sunxin
 * @date 2018/10/25 5:08 PM
 * @desc 自定义字体
 */
public class EcFontModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcon.values();
    }
}
