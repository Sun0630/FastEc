package com.sunxin.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.sunxin.core.app.Globle;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;

/**
 * @author sunxin
 * @date 2018/10/26 9:51 AM
 * @desc
 */
public class ExampleDelegate extends CommonDelegate {
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
                .url("https://news.baidu.com")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Globle.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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
