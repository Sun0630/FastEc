package com.sunxin.core.delegates.web.event;

import com.orhanobut.logger.Logger;

/**
 * @author sunxin
 * @date 2018/11/9 10:53 AM
 * @desc
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Logger.e("UndefineEvent", params);
        return null;
    }
}
