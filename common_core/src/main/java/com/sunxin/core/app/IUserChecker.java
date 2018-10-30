package com.sunxin.core.app;

/**
 * @author sunxin
 * @date 2018/10/30 9:38 PM
 * @desc 登陆和没有登陆
 */
public interface IUserChecker {

    void onSignIn();

    void onNotSignIn();
}
