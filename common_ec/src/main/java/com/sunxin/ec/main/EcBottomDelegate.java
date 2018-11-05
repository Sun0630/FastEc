package com.sunxin.ec.main;

import android.graphics.Color;

import com.sunxin.core.delegates.bottom.BaseBottomDelegate;
import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.core.delegates.bottom.BaseTabBean;
import com.sunxin.core.delegates.bottom.ItemBuilder;
import com.sunxin.ec.main.index.IndexDelegate;
import com.sunxin.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @author sunxin
 * @date 2018/11/2 3:10 PM
 * @desc
 */
public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public LinkedHashMap<BottomItemDelegate, BaseTabBean> setItems(ItemBuilder itemBuilder) {
        final LinkedHashMap<BottomItemDelegate, BaseTabBean> item = new LinkedHashMap<>();
        item.put(new IndexDelegate(), new BaseTabBean("{fa-home}", "首页"));
        item.put(new SortDelegate(), new BaseTabBean("{fa-sort}", "分类"));
        item.put(new IndexDelegate(), new BaseTabBean("{fa-compass}", "发现"));
        item.put(new IndexDelegate(), new BaseTabBean("{fa-shopping-cart}", "购物车"));
        item.put(new IndexDelegate(), new BaseTabBean("{fa-user}", "我的"));
        return itemBuilder.addItems(item).build();
    }
}
