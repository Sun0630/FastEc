package com.sunxin.core.app;

import com.sunxin.core.util.storage.CommonPreference;

/**
 * @author sunxin
 * @date 2018/10/30 9:38 PM
 * @desc 账户管理
 */
public class AccountManager {


    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登陆状态，登陆成功后调用
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        CommonPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSingIn() {
        return CommonPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


    /**
     * 检查是否登陆
     *
     * @param checker
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSingIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

}
