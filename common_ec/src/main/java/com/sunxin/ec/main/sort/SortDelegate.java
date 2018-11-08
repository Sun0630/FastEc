package com.sunxin.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.ec.R;
import com.sunxin.ec.main.sort.content.ContentDelegate;
import com.sunxin.ec.main.sort.list.VerticalListDelegate;

/**
 * @author sunxin
 * @date 2018/11/2 3:11 PM
 * @desc 分类
 */
public class SortDelegate extends BottomItemDelegate {

    @Override
    public void onClick(View v) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate verticalListDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, verticalListDelegate);
        // 设置右侧内容第一个显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentDelegate.newInstance(1));
    }
}
