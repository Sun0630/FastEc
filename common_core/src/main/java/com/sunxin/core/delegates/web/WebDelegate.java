package com.sunxin.core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;
import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author sunxin
 * @date 2018/11/8 5:18 PM
 * @desc webView统一封装
 */
public abstract class WebDelegate extends CommonDelegate implements IWebViewInitializer {

    private WebView mWebView = null;

    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();

    private String mUrl = null;

    private boolean mIsWebViewAvaliable = false;

    private CommonDelegate mTopDelegate = null;

    public WebDelegate() {
    }

    public abstract IWebViewInitializer setInitializer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }


    public void setTopDelegate(CommonDelegate delegate) {
        mTopDelegate = delegate;
    }

    public CommonDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
            Logger.e("======");
        }
        return mTopDelegate;
    }


    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = (String) Globle.getConfigrations().get(ConfigType.JAVASCRIPT_INTERFACE.name());
                mWebView.addJavascriptInterface(CommonWebInterface.create(this), name);
                mIsWebViewAvaliable = true;
            } else {
                throw new NullPointerException("Initializer is null!!!");
            }
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null!");
        }

        return mIsWebViewAvaliable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("url is null");
        }

        return mUrl;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvaliable = false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
