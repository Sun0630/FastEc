package com.sunxin.core.net.rx;

import android.content.Context;

import com.sunxin.core.net.HttpMethod;
import com.sunxin.core.net.RestCreator;
import com.sunxin.core.ui.loader.CommonLoader;
import com.sunxin.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author sunxin
 * @date 2018/10/26 1:53 PM
 * @desc 网络请求客户端
 */
public class RxRestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();


    private final RequestBody BODY;

    private final Context CONTEXT;

    private final LoaderStyle LOADER_STYLE;

    private final File FILE;


    public RxRestClient(String url,
                        WeakHashMap<String, Object> params,
                        RequestBody body,
                        LoaderStyle loaderStyle,
                        Context context,
                        File file
    ) {

        this.URL = url;
        PARAMS.putAll(params);
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
    }


    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }


    public Observable<String> request(HttpMethod method) {

        RxRestService restService = RestCreator.getRxRestService();

        Observable<String> observable = null;



        if (LOADER_STYLE != null){
            CommonLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = restService.get(URL, PARAMS);
                break;
            case POST:
                observable = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = restService.postRaw(URL,BODY);
                break;
            case PUT:
                observable = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = restService.putRaw(URL,BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("fiel",FILE.getName(),requestBody);
                observable = restService.upload(URL,body);
                break;
            case DELETE:
                observable = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        return observable;

    }




    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
           return request(HttpMethod.PUT);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
           return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
       return request(HttpMethod.DELETE);
    }


    public final Observable<ResponseBody> download(){

        Observable<ResponseBody> observable = RestCreator.getRxRestService().download(URL, PARAMS);
        return observable;
    }

}
