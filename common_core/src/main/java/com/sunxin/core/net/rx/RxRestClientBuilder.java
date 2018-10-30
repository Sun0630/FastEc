package com.sunxin.core.net.rx;

import android.content.Context;

import com.sunxin.core.net.RestCreator;
import com.sunxin.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.HashMap;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author sunxin
 * @date 2018/10/26 2:45 PM
 * @desc 建造者模式构建参数
 */
public class RxRestClientBuilder {


    private String mUrl;

    private static WeakHashMap<String, Object> mParams = RestCreator.getParams();

    private RequestBody mBody;

    private LoaderStyle mLoaderStyle;

    private Context mContext;

    private File mFile;



    RxRestClientBuilder() {

    }


    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }


    public final RxRestClientBuilder params(HashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }


    public final RxRestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<String, Object>();
        }
        mParams.put(key, value);

        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }



    /**
     * 设置加载动画
     * @param context
     * @param loaderStyle
     * @return
     */
    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }


    /**
     * 上传文件
     * @param file
     * @return
     */
    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }


    public final RxRestClientBuilder file(String filePath){
        this.mFile = new File(filePath);
        return this;
    }


    public RxRestClient build(){
        return new RxRestClient(mUrl,mParams,mBody,mLoaderStyle,mContext,mFile);
    }

}
