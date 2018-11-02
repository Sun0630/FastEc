package com.sunxin.core.wechat.templates;

import com.sunxin.core.wechat.BaseWxEntryActivity;
import com.sunxin.core.wechat.WeChat;

/**
 * @author sunxin
 * @date 2018/10/31 5:42 PM
 * @desc 设置为透明的
 */
public class WXEntryTemplate extends BaseWxEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        // 取消转场动画
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        // 获取到用户信息
        WeChat
                .getInstance()
                .getSignInCallback()
                .onSignInSuccess(userInfo);
    }
}
