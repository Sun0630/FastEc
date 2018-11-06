package com.sunxin.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author sunxin
 * @date 2018/11/6 11:28 AM
 * @desc 商品详情
 */
public class GoodsDetailDelegate extends CommonDelegate {


    public static GoodsDetailDelegate create() {
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 左右动画
        return new DefaultHorizontalAnimator();
    }
}
