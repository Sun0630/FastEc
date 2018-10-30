package com.sunxin.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.util.log.LatteLogger;

/**
 * @author sunxin
 * @date 2018/10/26 9:51 AM
 * @desc
 */
public class ExampleDelegate extends CommonDelegate {
    public static final String TAG = "Example";

    @Override
    public Object setLayout() {
        return R.layout.delegate_example_layout;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRetrofitClient();
    }


    public void testRetrofitClient() {
        RestClient
                .builder()
                .url("index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLogger.json(TAG, response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int errorCode, String errorMsg) {

                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build()
                .get();
    }

}
