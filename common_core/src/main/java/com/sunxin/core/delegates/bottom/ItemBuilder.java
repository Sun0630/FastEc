package com.sunxin.core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * @author sunxin
 * @date 2018/11/2 11:28 AM
 * @desc 建立Tabbean与Item的联系
 */
public final class ItemBuilder {

    private LinkedHashMap<BottomItemDelegate, BaseTabBean> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomItemDelegate itemDelegate, BaseTabBean tabBean) {
        ITEMS.put(itemDelegate,tabBean);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomItemDelegate,BaseTabBean> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomItemDelegate,BaseTabBean> build() {
        return ITEMS;
    }

}
