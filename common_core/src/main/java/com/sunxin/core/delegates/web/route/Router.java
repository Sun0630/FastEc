package com.sunxin.core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.delegates.web.WebDelegate;
import com.sunxin.core.delegates.web.WebDelegateImpl;

/**
 * @author sunxin
 * @date 2018/11/8 9:20 PM
 * @desc 路由者
 */
public class Router {

    private Router() {
    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 处理url
     *
     * @param delegate
     * @param url
     * @return
     */
    public final boolean handleUrl(WebDelegate delegate, String url) {

        // 如果是电话协议，直接跳转到拨号页面
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }


        final CommonDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);

        // 父delegate
//        final CommonDelegate parentDelegate = delegate.getParentDelegate();
//
//        if (parentDelegate == null) {
//            delegate.start(webDelegate);
//        } else {
//            parentDelegate.start(webDelegate);
//        }

        return true;
    }


    /**
     * 加载web界面
     *
     * @param webView
     * @param url
     */
    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }


    /**
     * 加载本地页面
     *
     * @param webView
     * @param url
     */
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }


    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }


    /**
     * 跳转拨打电话页面
     *
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }

}
