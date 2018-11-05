package com.sunxin.core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * @author sunxin
 * @date 2018/10/25 3:12 PM
 * @desc
 */
public final class Globle {

    public static Configrator init(Context context){
        getConfigrations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configrator.getInsance();
    }



    public static HashMap<String,Object> getConfigrations(){
        return Configrator.getInsance().getConfigs();
    }

    public static <T> T getConfigrator(Object key){
        return Configrator.getInsance().getConfigration(key);
    }


    public static Context getApplicationContext(){
        return (Context) getConfigrations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


    /**
     * 获取Handler
     * @return
     */
    public static Handler getHandler(){
        return (Handler) getConfigrations().get(ConfigType.HANDLER.name());
    }



}
