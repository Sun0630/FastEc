package com.sunxin.core.net;

import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author sunxin
 * @date 2018/10/26 2:19 PM
 * @desc
 */
public class RestCreator {

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 单例构建Retorfit
     */
    private static final class RetrofitHolder{

        private static final String BASE_URL = (String) Globle.getConfigrations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    /**
     * 单例构建OkHttp
     */
    private static final class OkHttpHolder{

        public static final int CONNECT_TIME_OUT = 60;
        public static final int READ_TIME_OUT = 60;
        public static final int WRITE_TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT,TimeUnit.SECONDS)
                .build();

    }

    /**
     * 单例构建RestService
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }



    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }


    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }



}
