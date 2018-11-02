package com.sunxin.core.delegates.bottom;

/**
 * @author sunxin
 * @date 2018/11/2 11:23 AM
 * @desc
 */
public class BaseTabBean {

    private final CharSequence ICON;

    private final CharSequence TITLE;

    public BaseTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
