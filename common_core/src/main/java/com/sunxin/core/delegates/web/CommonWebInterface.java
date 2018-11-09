package com.sunxin.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.sunxin.core.delegates.web.event.Event;
import com.sunxin.core.delegates.web.event.EventManager;

/**
 * @author sunxin
 * @date 2018/11/8 8:58 PM
 * @desc 与原生交互的接口
 */
public class CommonWebInterface {

    private final WebDelegate mDelegate;

    public CommonWebInterface(WebDelegate delegate) {
        mDelegate = delegate;
    }

    static CommonWebInterface create(WebDelegate delegate){
        return new CommonWebInterface(delegate);
    }


    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setContext(mDelegate.getContext());
            event.setDelegate(mDelegate);
            event.setUrl(mDelegate.getUrl());
            event.execute(params);
        }
        return null;
    }

}
