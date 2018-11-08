package com.sunxin.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author sunxin
 * @date 2018/11/5 2:01 PM
 * @desc
 */
public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE =
            new ReferenceQueue<>();

    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS =
            new LinkedHashMap<>();

    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<?, ?> map) {
        FIELDS_REFERENCE.get().putAll(map);
    }

    public static final MultipleEntityBuilder builder() {
        return new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }


    /**
     * 获取值
     *
     * @param key
     * @param <T>
     * @return
     */
    public final <T> T getField(Object key) {

        return (T) FIELDS_REFERENCE.get().get(key);
    }


    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    public final MultipleItemEntity setField(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    /**
     * 获取这个值的map
     *
     * @return
     */
    public final LinkedHashMap<?, ?> getFields() {

        return FIELDS_REFERENCE.get();
    }

}
