package com.sunxin.core.ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.sunxin.core.R;

/**
 * @author sunxin
 * @date 2018/10/29 5:20 PM
 * @desc
 */
public class LauncherHolderCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image;
    }
}
