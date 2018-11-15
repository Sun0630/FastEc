package com.sunxin.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sunxin.core.delegates.web.chromeclient.WebChromeClientImpl;
import com.sunxin.core.delegates.web.client.WebViewClientImpl;
import com.sunxin.core.delegates.web.route.RouteKeys;
import com.sunxin.core.delegates.web.route.Router;

/**
 * @author sunxin
 * @date 2018/11/8 9:10 PM
 * @desc
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        mPageLoadListener = pageLoadListener;
    }


    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(args);
        return webDelegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }


    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            // 用原生的方式模拟跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setPageLoadListener(mPageLoadListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
