package com.sunxin.core.net;

import android.content.Context;

import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IFailer;
import com.sunxin.core.net.callback.IRequest;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.ui.LoaderStyle;

import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author sunxin
 * @date 2018/10/26 2:45 PM
 * @desc 建造者模式构建参数
 */
public class RestClientBuilder {


    private String mUrl;

    private static WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private ISuccess mSuccess;

    private IError mError;

    private IFailer mFailer;

    private IRequest mRequest;

    private RequestBody mBody;

    private LoaderStyle mLoaderStyle;

    private Context mContext;


    RestClientBuilder() {

    }


    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }


    public final RestClientBuilder params(HashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }


    public final RestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<String, Object>();
        }
        mParams.put(key, value);

        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder failer(IFailer iFailer) {
        this.mFailer = iFailer;
        return this;
    }

    /**
     * 设置加载动画
     * @param context
     * @param loaderStyle
     * @return
     */
    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public RestClient build(){
        return new RestClient(mUrl,mParams,mSuccess,mError,mFailer,mRequest,mBody,mLoaderStyle,mContext);
    }

}
