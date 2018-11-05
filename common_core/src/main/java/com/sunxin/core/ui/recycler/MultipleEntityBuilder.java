package com.sunxin.core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * @author sunxin
 * @date 2018/11/5 2:32 PM
 * @desc
 */
public class MultipleEntityBuilder {

    private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {

        return new MultipleItemEntity(FIELDS);
    }

}
