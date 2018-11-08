package com.sunxin.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunxin
 * @date 2018/11/8 2:40 PM
 * @desc 右侧数据转换
 */
public class SectionDataConverter {

    public List<SectionBean> convert(String json) {

        final List<SectionBean> dataList = new ArrayList<>();

        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String sectionTitle = data.getString("section");

            // 添加title
            SectionBean sectionTitleBean = new SectionBean(true, sectionTitle);
            sectionTitleBean.setId(id);
            sectionTitleBean.setMore(true);

            dataList.add(sectionTitleBean);

            //添加内容
            final JSONArray goodsArray = data.getJSONArray("goods");
            int goodsSize = goodsArray.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject goodsItem = goodsArray.getJSONObject(j);
                final int goodsId = goodsItem.getInteger("goods_id");
                final String goodsName = goodsItem.getString("goods_name");
                final String goodsThumb = goodsItem.getString("goods_thumb");

                SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);

                dataList.add(new SectionBean(itemEntity));

            }

        }

        return dataList;
    }

}
