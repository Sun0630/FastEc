package com.sunxin.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;

/**
 * @author sunxin
 * @date 2018/10/26 9:51 AM
 * @desc
 */
public class ExampleDelegate extends CommonDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example_layout;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
