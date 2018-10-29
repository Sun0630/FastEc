package com.sunxin.core.net.download;

import android.os.AsyncTask;

import com.sunxin.core.net.RestCreator;
import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IFailer;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author sunxin
 * @date 2018/10/28 3:26 PM
 * @desc 处理下载
 */
public class DownloadHandler {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final ISuccess SUCCESS;

    private final IError ERROR;

    private final IFailer FAILER;

    private final IRequest REQUEST;

    private final String NAME;

    private final String DOWNLOAD_DIR;

    private final String EXTENSION;


    public DownloadHandler(String url,
                           ISuccess success,
                           IError error,
                           IFailer failer,
                           IRequest request,
                           String name,
                           String download_dir,
                           String extension) {
        this.URL = url;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILER = failer;
        this.REQUEST = request;
        this.NAME = name;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
    }


    public final void handleDownload(){
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator
                .getRestService()
                .download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            SaveFileTask saveFileTask = new SaveFileTask(SUCCESS,REQUEST);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,NAME,responseBody);

                            // 注意
                            if (saveFileTask.isCancelled()){
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILER != null){
                            FAILER.onFailer();
                        }
                    }
                });
    }


}
