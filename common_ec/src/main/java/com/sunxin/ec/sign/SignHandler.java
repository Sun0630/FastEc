package com.sunxin.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.app.AccountManager;
import com.sunxin.ec.database.DatabaseManager;
import com.sunxin.ec.database.UserProfile;

/**
 * @author sunxin
 * @date 2018/10/30 3:14 PM
 * @desc
 */
public class SignHandler {

    /**
     * 登陆
     * @param response
     * @param signListener
     */
    public static void onSignIn(String response,ISignListener signListener){

        JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        //插入数据库
        DatabaseManager.getInstance().getUserProfileDao().insertOrReplace(userProfile);

        // 设置登陆状态为true
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    /**
     * 注册
     * @param response
     * @param signListener
     */
    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserProfileDao().insertOrReplace(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
