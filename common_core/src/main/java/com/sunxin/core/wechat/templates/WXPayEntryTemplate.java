package com.sunxin.core.wechat.templates;

import android.widget.Toast;

import com.sunxin.core.wechat.BaseWxPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;

/**
 * @author sunxin
 * @date 2018/10/31 5:48 PM
 * @desc
 */
public class WXPayEntryTemplate extends BaseWxPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onReq(BaseReq req) {

    }
}
