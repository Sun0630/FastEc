package com.sunxin.ec.main.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.ui.recycler.DataConverter;
import com.sunxin.core.ui.recycler.ItemType;
import com.sunxin.core.ui.recycler.MultipleFields;
import com.sunxin.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/11/5 2:47 PM
 * @desc 首页数据的转换器
 */
public class IndexDataConverter extends DataConverter {

    @Override
    protected ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSONArray.parseObject(getJsonData()).getJSONArray("data");

        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final Integer id = data.getInteger("goodsId");
            final Integer spanSize = data.getInteger("spanSize");
            final String text = data.getString("text");
            final String imageUrl = data.getString("imageUrl");
            final JSONArray banners = data.getJSONArray("banners");

            // 处理banners
            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                // 初始化banner
                int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String bannerUrl = banners.getString(j);
                    bannerImages.add(bannerUrl);
                }
            }


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(type)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
