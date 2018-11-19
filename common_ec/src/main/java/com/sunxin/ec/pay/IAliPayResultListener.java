package com.sunxin.ec.pay;

/**
 * @author sunxin
 * @date 2018/11/19 11:20 AM
 * @desc
 */
public interface IAliPayResultListener {

    void onPaying();

    void onPaySucess();

    void onPayFail();

    void onPayCancle();

    void onPayConnectError();

}
