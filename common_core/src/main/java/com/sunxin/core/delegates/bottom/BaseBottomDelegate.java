package com.sunxin.core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.sunxin.core.R;
import com.sunxin.core.R2;
import com.sunxin.core.delegates.CommonDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author sunxin
 * @date 2018/11/2 11:07 AM
 * @desc bottomBar基类
 */
public abstract class BaseBottomDelegate extends CommonDelegate implements View.OnClickListener {

    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATES = new ArrayList<>();
    private final ArrayList<BaseTabBean> TAB_BEANS = new ArrayList<>();
    private final LinkedHashMap<BottomItemDelegate, BaseTabBean> ITEMS = new LinkedHashMap<>();


    @BindView(R2.id.bottom_bar_container)
    ContentFrameLayout mBottomBarContainer;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    /**
     * 当前是哪个Delegate
     */
    private int mCurrentDelegate = 0;

    /**
     * 初始进入的时候的Delegate
     */
    private int mIndexDelegate = 0;

    /**
     * 点击的颜色
     */
    private int mClickedColor = Color.RED;

    /**
     * 设置初始
     *
     * @return
     */
    public abstract int setIndexDelegate();

    /**
     * 设置颜色
     *
     * @return
     */
    @ColorInt
    public abstract int setClickedColor();

    public abstract LinkedHashMap<BottomItemDelegate, BaseTabBean> setItems(ItemBuilder itemBuilder);


    @Override
    public Object setLayout() {
        return R.layout.bottom_delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();

        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder itemBuilder = ItemBuilder.builder();

        final LinkedHashMap<BottomItemDelegate, BaseTabBean> items = setItems(itemBuilder);

        ITEMS.putAll(items);

        for (Map.Entry<BottomItemDelegate, BaseTabBean> entry : ITEMS.entrySet()) {
            final BottomItemDelegate itemDelegate = entry.getKey();
            final BaseTabBean tabBean = entry.getValue();

            ITEMS_DELEGATES.add(itemDelegate);
            TAB_BEANS.add(tabBean);
        }
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            // 为条目设计点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            final IconTextView item_icon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView item_title = (AppCompatTextView) item.getChildAt(1);

            BaseTabBean tabBean = TAB_BEANS.get(i);
            item_icon.setText(tabBean.getIcon());
            item_title.setText(tabBean.getTitle());

            if (i == mIndexDelegate) {
                item_icon.setTextColor(mClickedColor);
                item_title.setTextColor(mClickedColor);
            }
        }

        final SupportFragment[] delegateArray = ITEMS_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_container, mIndexDelegate, delegateArray);

    }


    /**
     * 重置颜色
     */
    private void resetColor() {
        int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView item_icon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView item_title = (AppCompatTextView) item.getChildAt(1);
            item_icon.setTextColor(Color.GRAY);
            item_title.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView item_icon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView item_title = (AppCompatTextView) item.getChildAt(1);
        item_icon.setTextColor(mClickedColor);
        item_title.setTextColor(mClickedColor);
        // 显示和隐藏Fragment
        showHideFragment(ITEMS_DELEGATES.get(tag),ITEMS_DELEGATES.get(mIndexDelegate));
        // 注意顺序
        mIndexDelegate = tag;

    }
}
