package com.sunxin.core.net;

import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IFailer;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.net.callback.RequestCallback;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author sunxin
 * @date 2018/10/26 1:53 PM
 * @desc 网络请求客户端
 */
public class RestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailer FAILER;

    private final IRequest REQUEST;

    private final RequestBody BODY;


    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      ISuccess success,
                      IError error,
                      IFailer failer,
                      IRequest request,
                      RequestBody body) {

        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILER = failer;
        this.REQUEST = request;
        this.BODY = body;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    public void request(HttpMethod method) {

        RestService restService = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = restService.get(URL,PARAMS);
                break;
            case POST:
                call = restService.post(URL,PARAMS);
                break;
            case PUT:
                call = restService.put(URL,PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }


    /**
     * 获取回调
     * @return
     */
    public Callback<String> getRequestCallback(){
        return new RequestCallback(SUCCESS,ERROR,FAILER,REQUEST);
    }


    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
