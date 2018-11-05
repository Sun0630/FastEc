package com.sunxin.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.sunxin.core.app.Globle;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.util.log.LatteLogger;

/**
 * @author sunxin
 * @date 2018/11/5 10:52 AM
 * @desc 刷新助手
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {


    private final SwipeRefreshLayout mRefreshLayout;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        mRefreshLayout = refreshLayout;
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
        RestClient
                .builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Globle.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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
