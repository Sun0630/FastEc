package com.sunxin.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.ui.recycler.DataConverter;
import com.sunxin.core.ui.recycler.ItemType;
import com.sunxin.core.ui.recycler.MultipleFields;
import com.sunxin.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/11/7 3:23 PM
 * @desc 分类左侧列表
 */
public final class VerticalListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");

        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.TAG, false)
                    .build();


            dataList.add(entity);
            // 设置进入默认选中第一个
            dataList.get(0).setField(MultipleFields.TAG,true);

        }

        return dataList;
    }
}
