package com.sunxin.core.wechat;

import android.app.Activity;

import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author sunxin
 * @date 2018/10/31 10:28 PM
 * @desc
 */
public class WeChat {

    public final static String APP_ID = Globle.getConfigrator(ConfigType.WX_APP_ID);

    public final static String APP_SECRET = Globle.getConfigrator(ConfigType.WX_APP_SECRET);

    private final IWXAPI mWXAPI;

    private WeChat() {
        Activity activity = Globle.getConfigrator(ConfigType.ACTIVITY);
        mWXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        mWXAPI.registerApp(APP_ID);
    }

    public IWXAPI getIWXAPI() {
        return mWXAPI;
    }

    private static final class HOLDER {
        private static final WeChat INSTANCE = new WeChat();
    }


    public static WeChat getInstance() {
        return HOLDER.INSTANCE;
    }

    /**
     * 向微信服务器发送请求
     */
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        mWXAPI.sendReq(req);
    }


}
