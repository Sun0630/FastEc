package com.sunxin.core.net;

import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;
import com.sunxin.core.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author sunxin
 * @date 2018/10/26 2:19 PM
 * @desc
 */
public final class RestCreator {

    public static RestService getRestService() {

        return RestServiceHolder.REST_SERVICE;
    }

    public static RxRestService getRxRestService() {

        return RxRestServiceHolder.Rx_REST_SERVICE;
    }

    /**
     * 单例构建Retorfit
     */
    private static final class RetrofitHolder {

        private static final String BASE_URL = (String) Globle.getConfigrations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     * 单例构建OkHttp
     */
    private static final class OkHttpHolder {

        private static final int CONNECT_TIME_OUT = 60;
        private static final int READ_TIME_OUT = 60;
        private static final int WRITE_TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final ArrayList<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) Globle.getConfigrations().get(ConfigType.INTERCEPTORS.name());

        private static final OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS != null) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    /**
     * 单例构建RestService
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }

    /**
     * 构建RxJava相关的
     */
    private static final class RxRestServiceHolder {
        private static final RxRestService Rx_REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);

    }


    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }


    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


}
