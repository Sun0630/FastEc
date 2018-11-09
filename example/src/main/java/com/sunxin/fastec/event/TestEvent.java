package com.sunxin.fastec.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.sunxin.core.delegates.web.event.Event;

/**
 * @author sunxin
 * @date 2018/11/9 11:29 AM
 * @desc
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    // main thread 调用Js代码
                    webView.evaluateJavascript("nativeCall();", null);

                }
            });
        }
        return null;
    }
}
