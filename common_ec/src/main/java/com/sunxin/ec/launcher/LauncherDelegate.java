package com.sunxin.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.ui.launcher.ScrollLauncherTag;
import com.sunxin.core.util.storage.CommonPreference;
import com.sunxin.core.util.timer.BaseTimerTask;
import com.sunxin.core.util.timer.ITimerListener;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sunxin
 * @date 2018/10/29 2:58 PM
 * @desc 启动页Fragment
 */
public class LauncherDelegate extends CommonDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvLauncherTimer;

    private Timer mTimer = null;

    private int mCount = 5;


    @OnClick(R2.id.tv_launcher_timer)
    public void onViewClicked() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    /**
     * 初始化倒计时
     */
    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        mTimer.schedule(baseTimerTask, 0, 1000);
    }

    /**
     * 检查是否进入启动页
     */
    private void checkIsShowScroll(){
        if (!CommonPreference.getAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            // 检查用户是否登陆
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvLauncherTimer != null) {
                    mTvLauncherTimer.setText(MessageFormat.format("跳过 \n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
