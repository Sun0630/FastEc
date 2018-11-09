package com.sunxin.core.net.rx;

import com.sunxin.core.util.storage.CommonPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author sunxin
 * @date 2018/11/9 5:52 PM
 * @desc cookie拦截器
 */
public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(CommonPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        // 为原生Api请求带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie", cookie);
                    }
                });

        return chain.proceed(builder.build());
    }
}
