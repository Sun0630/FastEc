package com.sunxin.core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * @author sunxin
 * @date 2018/11/6 9:49 AM
 * @desc 分割线
 */
public class BaseItemDecoration extends DividerItemDecoration {


    private BaseItemDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookUpImpl(color, size));
    }

    public static BaseItemDecoration create(@ColorInt int color, int size) {
        return new BaseItemDecoration(color, size);
    }


}
