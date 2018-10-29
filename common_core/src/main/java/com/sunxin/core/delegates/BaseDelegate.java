package com.sunxin.core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunxin.core.activity.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author sunxin
 * @date 2018/10/26 9:11 AM
 * @desc
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;

        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }else {
            throw new RuntimeException("setLayout() type must be view or int ");
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState,rootView);
        }

        return rootView;
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
