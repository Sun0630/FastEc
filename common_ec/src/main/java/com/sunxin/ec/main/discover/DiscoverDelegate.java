package com.sunxin.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.core.delegates.web.WebDelegateImpl;
import com.sunxin.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author sunxin
 * @date 2018/11/8 5:11 PM
 * @desc 发现页面
 */
public class DiscoverDelegate extends BottomItemDelegate {


    @Override
    public void onClick(View v) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        final WebDelegateImpl webDelegate = WebDelegateImpl.create("index.html");
//        String url = "http://s.kaiyuncare.com/html/testclick.html";
//        String url = "http://s.kaiyuncare.com/question/toStrokeQaPageSuccess?userId=347&questionnaireId=8&headerId=87";
        String url = "index.html";
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        webDelegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container,webDelegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
