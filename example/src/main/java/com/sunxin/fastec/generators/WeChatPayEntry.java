package com.sunxin.fastec.generators;

import com.sunxin.annotations.PayEntryGenerator;
import com.sunxin.core.wechat.templates.WXPayEntryTemplate;

/**
 * @author sunxin
 * @date 2018/10/31 5:54 PM
 * @desc
 */
@PayEntryGenerator(
        packageName = "com.sunxin.fastec",
        payEntryTemplate = WXPayEntryTemplate.class)
public class WeChatPayEntry {
}
