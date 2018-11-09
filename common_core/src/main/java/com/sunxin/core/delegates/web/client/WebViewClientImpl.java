package com.sunxin.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.orhanobut.logger.Logger;
import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;
import com.sunxin.core.delegates.web.IPageLoadListener;
import com.sunxin.core.delegates.web.WebDelegate;
import com.sunxin.core.delegates.web.route.Router;
import com.sunxin.core.ui.loader.CommonLoader;
import com.sunxin.core.ui.loader.LoaderStyle;
import com.sunxin.core.util.storage.CommonPreference;

/**
 * @author sunxin
 * @date 2018/11/8 9:18 PM
 * @desc
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate mDelegate;

    private IPageLoadListener mPageLoadListener = null;

    private static final Handler HANDLER = Globle.getHandler();

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        mPageLoadListener = pageLoadListener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Logger.e("shouldOverrideUrlLoading", url);
        // 返回true表示url的跳转由原生接管并处理
        return Router.getInstance().handleUrl(mDelegate, url);
    }


    /**
     * 同步Cookie，获取浏览器Cookie
     */
    private void syncCookie() {
        final CookieManager cookieManager = CookieManager.getInstance();
        /**
         * 注意：这里的cookie和API请求的Cookie是不一样的，这个在网页不可见
         */
        final String webHost = (String) Globle.getConfigrations().get(ConfigType.WEB_HOST.name());
        if (webHost != null) {
            if (cookieManager.hasCookies()){
                final String cookie = cookieManager.getCookie(webHost);
                if (!TextUtils.isEmpty(cookie)){
                    CommonPreference.addCustomAppProfile("cookie",cookie);
                }
            }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadStart();
        }
        CommonLoader.showLoading(view.getContext(), LoaderStyle.BallScaleMultipleIndicator);
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mPageLoadListener != null) {
            mPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                CommonLoader.stopLoading();
            }
        }, 1000);
    }
}
