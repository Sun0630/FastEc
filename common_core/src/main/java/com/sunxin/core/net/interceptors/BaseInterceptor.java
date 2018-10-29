package com.sunxin.core.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * @author sunxin
 * @date 2018/10/29 10:30 AM
 * @desc 基础拦截器
 */
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取到Get请求后面拼接的参数的键值对并存放在Map中
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParams(Chain chain) {
        final HttpUrl url = chain.request().url();
        // 拿到url后面拼接参数的个数
        int size = url.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            // 获取到参数的键值存放在map中
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }

        return params;
    }

    /**
     * 重载一个
     *
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParams(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }


    /**
     * 从Post的请求体中获取到请求参数并保存到Map中
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParams(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }

        return params;
    }


    protected String getBodyParams(Chain chain, String key) {
        return getBodyParams(chain).get(key);
    }


}
