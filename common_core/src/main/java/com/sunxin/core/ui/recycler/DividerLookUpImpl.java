package com.sunxin.core.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @author sunxin
 * @date 2018/11/6 9:52 AM
 * @desc
 */
public class DividerLookUpImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;

    private final int SIZE;

    public DividerLookUpImpl(int color, int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .color(COLOR)
                .size(SIZE)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .color(COLOR)
                .size(SIZE)
                .build();
    }
}
