package com.sunxin.ec.sign;

/**
 * @author sunxin
 * @date 2018/10/30 9:43 PM
 * @desc 登陆监听
 */
public interface ISignListener {

    /**
     * 登陆成功
     */
    void onSignInSuccess();

    /**注册成功
     */
    void onSignUpSuccess();
}
