package com.sunxin.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.ec.R;

/**
 * @author sunxin
 * @date 2018/11/2 3:11 PM
 * @desc 首页
 */
public class IndexDelegate extends BottomItemDelegate {

    @Override
    public void onClick(View v) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
