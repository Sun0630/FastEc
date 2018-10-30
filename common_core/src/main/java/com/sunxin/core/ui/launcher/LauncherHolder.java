package com.sunxin.core.ui.launcher;


import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.sunxin.core.R;

/**
 * @author sunxin
 * @date 2018/10/29 5:21 PM
 * @desc
 */
public class LauncherHolder extends Holder<Integer> {

    private AppCompatImageView mImageView;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.iv_launcher);
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
