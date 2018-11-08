package com.sunxin.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.ui.recycler.ItemType;
import com.sunxin.core.ui.recycler.MultipleFields;
import com.sunxin.core.ui.recycler.MultipleItemEntity;
import com.sunxin.core.ui.recycler.MultipleRecyclerAdapter;
import com.sunxin.core.ui.recycler.MultipleViewHolder;
import com.sunxin.ec.R;
import com.sunxin.ec.main.sort.SortDelegate;
import com.sunxin.ec.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * @author sunxin
 * @date 2018/11/7 4:22 PM
 * @desc 分类的适配器
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;

    /**
     * 上一次点击的位置
     */
    private int mPrePosition = 0;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        // 添加垂直菜单列表
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);

    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String nameText = entity.getField(MultipleFields.NAME);
                final int id = entity.getField(MultipleFields.ID);
                final boolean isClick = entity.getField(MultipleFields.TAG);
                final TextView tvName = holder.getView(R.id.tv_vertical_item_name);
                final View viewLine = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentPosition = holder.getAdapterPosition();
                        if (currentPosition != mPrePosition) {
                            // 还原上次点击的
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            // 更新这次点击的
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);

                            mPrePosition = currentPosition;
                            // 获取id
                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);

                            showContent(contentId);
                        }
                    }
                });
                if (!isClick) {
                    viewLine.setVisibility(View.INVISIBLE);
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    viewLine.setVisibility(View.VISIBLE);
                    tvName.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                    viewLine.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                }
                holder.setText(R.id.tv_vertical_item_name, nameText);
                break;
            default:
                break;
        }

    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentDelegate delegate) {
        final CommonDelegate contentDelegate = SupportHelper
                .findFragment(DELEGATE.getChildFragmentManager(),ContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.getSupportDelegate().replaceFragment(delegate,false);
        }
    }
}
