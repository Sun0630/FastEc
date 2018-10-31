package com.sunxin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sunxin
 * @date 2018/10/31 4:33 PM
 * @desc
 */
// 加在类上面
@Target(ElementType.TYPE)
// 源码阶段
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplate();
}
