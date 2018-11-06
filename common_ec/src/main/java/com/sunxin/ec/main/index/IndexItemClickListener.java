package com.sunxin.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.detail.GoodsDetailDelegate;

/**
 * @author sunxin
 * @date 2018/11/6 11:10 AM
 * @desc 条目点击事件
 */
public class IndexItemClickListener extends SimpleClickListener {

    private CommonDelegate mDelegate;

    private IndexItemClickListener(CommonDelegate delegate) {
        mDelegate = delegate;
    }

    public static IndexItemClickListener create(CommonDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create();
        mDelegate.start(goodsDetailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }



}
