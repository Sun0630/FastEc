package com.sunxin.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.ui.recycler.MultipleItemEntity;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;
import com.sunxin.ec.main.sort.SortDelegate;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author sunxin
 * @date 2018/11/6 5:31 PM
 * @desc 左侧列表
 */
public class VerticalListDelegate extends CommonDelegate {

    @BindView(R2.id.rv_vertical_list)
    RecyclerView mRvVerticalList;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView(){
        mRvVerticalList.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 屏蔽动画效果
        mRvVerticalList.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient
                .builder()
                .url("http://127.0.0.1/sort_list")
                .loader(getActivity())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       final ArrayList<MultipleItemEntity> entities = new VerticalListDataConverter()
                                .setJsonData(response).convert();
                       final SortDelegate delegate = getParentDelegate();
                       SortRecyclerAdapter adapter = new SortRecyclerAdapter(entities,delegate);
                       mRvVerticalList.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
