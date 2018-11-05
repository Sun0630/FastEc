package com.sunxin.core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author sunxin
 * @date 2018/11/5 3:34 PM
 * @desc
 */
public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    /**
     * 简单工厂模式
     * @param view
     * @return
     */
    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
