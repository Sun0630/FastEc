package com.sunxin.fastec.generators;

import com.sunxin.annotations.AppRegisterGenerator;
import com.sunxin.core.wechat.templates.AppRegisterTemplate;

/**
 * @author sunxin
 * @date 2018/10/31 5:54 PM
 * @desc
 */
@AppRegisterGenerator(
        packageName = "com.sunxin.fastec",
        appRegsiterTemplate = AppRegisterTemplate.class)
public interface AppRegister {

}
