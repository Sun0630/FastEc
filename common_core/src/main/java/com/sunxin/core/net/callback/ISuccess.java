package com.sunxin.core.net.callback;

/**
 * @author sunxin
 * @date 2018/10/26 2:47 PM
 * @desc 成功回调
 */
public interface ISuccess {

    /**
     * 成功
     * @param response
     */
    void onSuccess(String response);
}
