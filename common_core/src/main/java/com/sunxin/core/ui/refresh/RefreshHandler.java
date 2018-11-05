package com.sunxin.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.app.Globle;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.ui.recycler.DataConverter;
import com.sunxin.core.ui.recycler.MultipleRecyclerAdapter;
import com.sunxin.core.util.log.LatteLogger;

/**
 * @author sunxin
 * @date 2018/11/5 10:52 AM
 * @desc 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout mRefreshLayout;

    private final RecyclerView mRecyclerView;

    private final DataConverter mConverter;

    private final PagingBean mPagingBean;

    private MultipleRecyclerAdapter mAdapter = null;

    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, DataConverter converter, PagingBean pagingBean) {
        mRefreshLayout = refreshLayout;
        mRecyclerView = recyclerView;
        mConverter = converter;
        mPagingBean = pagingBean;
        mRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        mRefreshLayout.setRefreshing(true);

        Globle.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 可以在这里进行网络请求
                mRefreshLayout.setRefreshing(false);
                LatteLogger.e("tag", "stopRefresh");
            }
        }, 2000);
    }

    public void firstPage(String url) {
        mPagingBean.setDelayed(1000);
        RestClient
                .builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject dadaObj = JSON.parseObject(response);
                        mPagingBean
                                .setTotal(dadaObj.getInteger("total"))
                                .setPageSize(dadaObj.getInteger("page_size"));
                        // 设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(mConverter.setJsonData(response));
                        mRecyclerView.setAdapter(mAdapter);
                        mPagingBean.addIndex();
                    }
                })
                .build()
                .get();
    }


    @Override
    public void onRefresh() {
        refresh();
    }
}
