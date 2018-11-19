package com.sunxin.ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.R;

/**
 * @author sunxin
 * @date 2018/11/19 11:20 AM
 * @desc
 */
public class FastPay {

    private IAliPayResultListener mAliPayResultListener = null;

    private Activity mActivity = null;
    private AlertDialog mAlertDialog = null;

    private FastPay(CommonDelegate delegate) {
        this.mActivity = delegate.getProxyActivity();
        this.mAlertDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(CommonDelegate delegate) {
        return new FastPay(delegate);
    }

    public void beginPayDialog() {
        mAlertDialog.show();
        final Window window = mAlertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            // 设置从下往上渐入
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
        }
    }


}
