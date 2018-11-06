package com.sunxin.core.ui.banner;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sunxin.core.R;

/**
 * @author sunxin
 * @date 2018/11/5 4:40 PM
 * @desc
 */
public class ImageHolder extends Holder<String> {

    private AppCompatImageView mImageView;

    private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.iv_banner);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(mImageView.getContext())
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }
}
