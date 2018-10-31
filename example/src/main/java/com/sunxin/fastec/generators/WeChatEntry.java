package com.sunxin.fastec.generators;

import com.sunxin.annotations.EntryGenerator;
import com.sunxin.core.wechat.templates.WXEntryTemplate;

/**
 * @author sunxin
 * @date 2018/10/31 5:54 PM
 * @desc
 */
@EntryGenerator(
        packageName = "com.sunxin.fastec",
        entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {
}
