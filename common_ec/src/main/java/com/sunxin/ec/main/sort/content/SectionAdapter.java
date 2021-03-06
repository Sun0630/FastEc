package com.sunxin.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunxin.ec.R;

import java.util.List;

/**
 * @author sunxin
 * @date 2018/11/8 3:23 PM
 * @desc 右侧内容的Adapter
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        final String name = item.t.getGoodsName();
        final String thumb = item.t.getGoodsThumb();
        final int id = item.t.getGoodsId();

        helper.setText(R.id.tv, name);
        final AppCompatImageView thumbView = helper.getView(R.id.iv);
        Glide
                .with(mContext)
                .load(thumb)
                .apply(
                        new RequestOptions()
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(thumbView);
    }
}
