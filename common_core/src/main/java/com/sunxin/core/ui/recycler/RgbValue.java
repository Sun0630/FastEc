package com.sunxin.core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @author sunxin
 * @date 2018/11/6 10:31 AM
 * @desc
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }
}
