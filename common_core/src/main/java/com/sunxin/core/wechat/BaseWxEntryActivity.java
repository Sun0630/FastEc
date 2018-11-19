package com.sunxin.core.wechat;

import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.IError;
import com.sunxin.core.net.callback.IFailer;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * @author sunxin
 * @date 2018/10/31 10:57 PM
 * @desc 微信登陆
 */
public abstract class BaseWxEntryActivity extends BaseWxActivity {


    /**
     * 用户登陆成功后的回调
     *
     * @param userInfo
     */
    protected abstract void onSignInSuccess(String userInfo);



    /**
     * 第三方应用发送请求到微信后的回调
     *
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        final String code = ((SendAuth.Resp) resp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(WeChat.APP_ID)
                .append("&secret=")
                .append(WeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        LatteLogger.d("authUrl", authUrl.toString());

        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient
                .builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject authObj = JSONObject.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();

                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        LatteLogger.d("userInfoUrl", userInfoUrl.toString());

                        getUserInfo(userInfoUrl.toString());

                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient
                .builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess (response);
                    }
                })
                .failer(new IFailer() {
                    @Override
                    public void onFailer() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int errorCode, String errorMsg) {

                    }
                })
                .build()
                .get();
    }
}
