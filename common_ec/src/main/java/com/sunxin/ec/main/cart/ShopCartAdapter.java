package com.sunxin.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.sunxin.core.app.Globle;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.ui.recycler.MultipleFields;
import com.sunxin.core.ui.recycler.MultipleItemEntity;
import com.sunxin.core.ui.recycler.MultipleRecyclerAdapter;
import com.sunxin.core.ui.recycler.MultipleViewHolder;
import com.sunxin.ec.R;

import java.util.List;

/**
 * @author sunxin
 * @date 2018/11/14 9:41 AM
 * @desc 购物车Adapter
 */
public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;

    private OnCartItemClickListener mItemClickListener;

    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();


    public void setItemClickListener(OnCartItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        // 初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double mTotal = count * price;
            mTotalPrice += mTotal;

        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setSelectedAll(boolean selectedAll) {
        mIsSelectedAll = selectedAll;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                // 取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final int count = entity.getField(ShopCartItemFields.COUNT);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                // 取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);
                // 赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);

                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);

                final boolean isSelect = entity.getField(ShopCartItemFields.IS_SELECTED);

                //处理选中状态
                if (isSelect) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Globle.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }

                // 设置点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Globle.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                // 增加和减少
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient
                                    .builder()
                                    .url("shop_cart_list")
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            final String count = tvCount.getText().toString();
                                            int countNum = Integer.parseInt(count);
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));

                                            if (mItemClickListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemPrice = price * countNum;
                                                mItemClickListener.onCartItemClick(itemPrice);
                                            }

                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartItemFields.COUNT);
                        RestClient
                                .builder()
                                .url("shop_cart_list")
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        final String count = tvCount.getText().toString();
                                        int countNum = Integer.parseInt(count);
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mItemClickListener != null) {
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemPrice = price * countNum;
                                            mItemClickListener.onCartItemClick(itemPrice);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                break;
            default:
                break;
        }
    }
}
