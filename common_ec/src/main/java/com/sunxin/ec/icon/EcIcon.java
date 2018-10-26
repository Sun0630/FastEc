package com.sunxin.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author sunxin
 * @date 2018/10/25 5:09 PM
 * @desc
 */
public enum EcIcon implements Icon {

    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    char character;

    EcIcon(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
