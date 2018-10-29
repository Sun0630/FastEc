package com.sunxin.core.net;

import android.content.Context;

import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IFailer;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.net.callback.RequestCallback;
import com.sunxin.core.net.download.DownloadHandler;
import com.sunxin.core.ui.CommonLoader;
import com.sunxin.core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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

    private final Context CONTEXT;

    private final LoaderStyle LOADER_STYLE;

    private final File FILE;

    private final String NAME;

    private final String DOWNLOAD_DIR;

    private final String EXTENSION;



    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      ISuccess success,
                      IError error,
                      IFailer failer,
                      IRequest request,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context,
                      File file,
                      String name,
                      String download_dir,
                      String extension) {

        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILER = failer;
        this.REQUEST = request;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.NAME = name;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
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

        if (LOADER_STYLE != null){
            CommonLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = restService.postRaw(URL,BODY);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL,BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("fiel",FILE.getName(),requestBody);
                call = restService.upload(URL,body);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
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
     *
     * @return
     */
    public Callback<String> getRequestCallback() {
        return new RequestCallback(SUCCESS, ERROR, FAILER, REQUEST,LOADER_STYLE);
    }


    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);

        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download(){
        new DownloadHandler(URL,SUCCESS,ERROR,FAILER,REQUEST,NAME,DOWNLOAD_DIR,EXTENSION)
                .handleDownload();
    }

}
