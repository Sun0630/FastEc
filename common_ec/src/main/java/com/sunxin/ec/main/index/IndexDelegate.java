package com.sunxin.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.core.ui.refresh.RefreshHandler;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;

import butterknife.BindView;

/**
 * @author sunxin
 * @date 2018/11/2 3:11 PM
 * @desc 首页
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSrlIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mEtSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar mTbIndex;

    private RefreshHandler mRefreshHandler;

    @Override
    public void onClick(View v) {

    }

    private void initRefreshLayout() {
        mSrlIndex.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        //设置下拉有缩放效果，起始位置
        mSrlIndex.setProgressViewOffset(true, 120, 300);

    }

    private void initRecyclerView(){
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        mRvIndex.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://127.0.0.1/index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mSrlIndex,mRvIndex,new IndexDataConverter());
    }
}
