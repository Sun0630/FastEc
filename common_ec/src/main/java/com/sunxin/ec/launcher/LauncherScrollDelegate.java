package com.sunxin.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.sunxin.core.app.AccountManager;
import com.sunxin.core.app.IUserChecker;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.ui.launcher.ILauncherFinishListener;
import com.sunxin.core.ui.launcher.LauncherHolderCreator;
import com.sunxin.core.ui.launcher.OnLauncherFinishTag;
import com.sunxin.core.ui.launcher.ScrollLauncherTag;
import com.sunxin.core.util.storage.CommonPreference;
import com.sunxin.ec.R;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/10/29 5:08 PM
 * @desc 启动页
 */
public class LauncherScrollDelegate extends CommonDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherFinishListener mLauncherFinishListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherFinishListener){
            mLauncherFinishListener = (ILauncherFinishListener) activity;
        }
    }


    private void initBanners() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanners();
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTEGERS.size() - 1) {
            //如果点击的是最后一个
            CommonPreference.setAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name(),true);
            // 检查用户是否已经登陆
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    // 已登陆
                    if (mLauncherFinishListener != null) {
                        mLauncherFinishListener.onLauncherFinished(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    // 未登陆
                    if (mLauncherFinishListener != null) {
                        mLauncherFinishListener.onLauncherFinished(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
