package com.sunxin.ec.main.cart;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunxin.core.ui.recycler.DataConverter;
import com.sunxin.core.ui.recycler.MultipleFields;
import com.sunxin.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author sunxin
 * @date 2018/11/14 9:20 AM
 * @desc
 */
public class ShopCartDataConverter extends DataConverter {



    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final JSONArray dataArray = JSONArray.parseObject(getJsonData()).getJSONArray("data");
        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject dataObj = dataArray.getJSONObject(i);
            final String title = dataObj.getString("title");
            final String desc = dataObj.getString("desc");
            final int id = dataObj.getInteger("id");
            final int count = dataObj.getInteger("count");
            final double price = dataObj.getDouble("price");
            final String thumb = dataObj.getString("thumb");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,thumb)
                    .setField(MultipleFields.ITEM_TYPE,ShopCartItemType.SHOP_CART_ITEM)
                    .setField(ShopCartItemFields.COUNT,count)
                    .setField(ShopCartItemFields.DESC,desc)
                    .setField(ShopCartItemFields.PRICE,price)
                    .setField(ShopCartItemFields.TITLE,title)
                    .setField(ShopCartItemFields.IS_SELECTED,false)
                    .setField(ShopCartItemFields.POSITION,i)
                    .build();

            dataList.add(entity);

        }

        return dataList;
    }
}
