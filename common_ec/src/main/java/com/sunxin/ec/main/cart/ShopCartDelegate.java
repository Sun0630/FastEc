package com.sunxin.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.sunxin.core.app.Globle;
import com.sunxin.core.delegates.bottom.BottomItemDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.ui.recycler.MultipleItemEntity;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;
import com.sunxin.ec.pay.FastPay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sunxin
 * @date 2018/11/12 2:05 PM
 * @desc 购物车
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, OnCartItemClickListener {

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRvShopCart;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mSelectAll;
    @BindView(R2.id.view_stub_empty)
    ViewStubCompat mStubEmpty;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice;


    private ShopCartAdapter mAdapter;
    private double mTotalPrice = 0.00;

    /**
     * 购物车数量标记
     */
    private int mCurrentCount = 0;

    private int mTotalCount = 0;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onSelectAllClick() {
        int tag = (int) mSelectAll.getTag();
        if (tag == 0) {
            // 选中,设置颜色为橙色
            mSelectAll.setTextColor(ContextCompat.getColor(Globle.getApplicationContext(), R.color.app_main));
            // 在Adapter中设置为全选状态
            mAdapter.setSelectedAll(true);
            // 设置tag
            mSelectAll.setTag(1);
            // 更新
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mSelectAll.setTextColor(Color.GRAY);
            mAdapter.setSelectedAll(false);
            mSelectAll.setTag(0);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }


    @OnClick(R2.id.tv_top_shop_cart_remove_seleted)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();

    }

    private void checkItemCount() {
        final int itemCount = mAdapter.getItemCount();
        if (itemCount == 0) {
            @SuppressLint("RestrictedApi") final View stubView = mStubEmpty.inflate();
            final AppCompatTextView tvToBuy = stubView.findViewById(R.id.stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "该去购物啦～～", Toast.LENGTH_SHORT).show();
                }
            });
            mRvShopCart.setVisibility(View.GONE);
        } else {
            mRvShopCart.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay(){
        //1. 创建订单
        FastPay.create(this).beginPayDialog();
    }

    private void createOrder(){

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mSelectAll.setTag(0);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient
                .builder()
                .url("shop_cart_list")
                .loader(_mActivity)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        ArrayList<MultipleItemEntity> data = new ShopCartDataConverter()
                .setJsonData(response).convert();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setItemClickListener(this);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText("¥ "+String.valueOf(mTotalPrice));
        mAdapter.closeLoadAnimation();
        mRvShopCart.setLayoutManager(layoutManager);
        mRvShopCart.setAdapter(mAdapter);
        checkItemCount();
    }

    @Override
    public void onCartItemClick(double itemTotalPrice) {
        final double totalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText("¥ "+String.valueOf(totalPrice));
    }
}
