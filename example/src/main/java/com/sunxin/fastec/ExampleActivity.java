package com.sunxin.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.sunxin.core.activity.ProxyActivity;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public CommonDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
