package com.sunxin.core.net.callback;

/**
 * @author sunxin
 * @date 2018/10/26 2:47 PM
 * @desc 错误回调
 */
public interface IError {

    /**
     * 错误回调
     * @param errorCode
     * @param errorMsg
     */
    void onError(int errorCode,String errorMsg);
}
