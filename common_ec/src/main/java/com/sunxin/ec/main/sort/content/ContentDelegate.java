package com.sunxin.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * @author sunxin
 * @date 2018/11/6 5:38 PM
 * @desc 右侧内容布局
 */
public class ContentDelegate extends CommonDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    private List<SectionBean> mData;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRvListContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mContentId = arguments.getInt(ARG_CONTENT_ID);
        }

    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    private void initData() {
        RestClient
                .builder()
                .url("http://127.0.0.1/sort_content_list?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        // 转换数据
                        mData = new SectionDataConverter().convert(response);
                        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content,
                                R.layout.item_section_header,mData);
                        mRvListContent.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRvListContent.setLayoutManager(layoutManager);
        initData();
    }
}
