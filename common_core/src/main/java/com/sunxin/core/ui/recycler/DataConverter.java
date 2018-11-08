package com.sunxin.core.ui.recycler;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/11/5 2:38 PM
 * @desc 数据转换
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData){
        this.mJsonData = jsonData;
        return this;
    }

    protected String getJsonData(){
        if (TextUtils.isEmpty(mJsonData)){
            throw new NullPointerException("DATA IS NULL!!!");
        }
        return mJsonData;
    }




}
