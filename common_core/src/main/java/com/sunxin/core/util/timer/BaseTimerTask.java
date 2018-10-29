package com.sunxin.core.util.timer;

import java.util.TimerTask;

/**
 * @author sunxin
 * @date 2018/10/29 3:01 PM
 * @desc 倒计时任务基类
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mTimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        mTimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mTimerListener != null) {
            mTimerListener.onTimer();
        }
    }
}
