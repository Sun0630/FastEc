package com.sunxin.core.ui.banner;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.sunxin.core.R;

/**
 * @author sunxin
 * @date 2018/11/5 4:40 PM
 * @desc
 */
public class HolderCreator implements CBViewHolderCreator {

    @Override
    public Holder createHolder(View itemView) {
        return new ImageHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image;
    }
}
