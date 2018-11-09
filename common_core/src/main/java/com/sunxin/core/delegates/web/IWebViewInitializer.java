package com.sunxin.core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author sunxin
 * @date 2018/11/8 5:33 PM
 * @desc
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    /**
     * 针对浏览器本身行为的控制
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 针对内部页面的控制
     * @return
     */
    WebChromeClient initWebChromeClient();

}
