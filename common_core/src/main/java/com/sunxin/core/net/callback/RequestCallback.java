package com.sunxin.core.net.callback;

import android.os.Handler;

import com.sunxin.core.ui.CommonLoader;
import com.sunxin.core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author sunxin
 * @date 2018/10/26 3:44 PM
 * @desc
 */
public class RequestCallback implements Callback<String> {

    private final ISuccess success;

    private final IError error;

    private final IFailer failer;

    private final IRequest request;

    private final LoaderStyle loaderStyle;


    private static Handler sHandler = new Handler();

    public RequestCallback(ISuccess success, IError error, IFailer failer, IRequest request,LoaderStyle loaderStyle) {
        this.success = success;
        this.error = error;
        this.failer = failer;
        this.request = request;
        this.loaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful() && call.isExecuted()){
            if (success != null) {
                success.onSuccess(response.body());
            }
        }else {
            if (error != null) {
                error.onError(response.code(),response.message());
            }
        }

        stopLoading();

    }



    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (failer != null) {
            failer.onFailer();
        }

        if (request != null) {
            request.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading() {
        if (loaderStyle!=null){
            sHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CommonLoader.stopLoading();
                }
            },2000);
        }
    }
}
