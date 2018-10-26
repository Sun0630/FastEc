package com.sunxin.fastec;

import com.sunxin.core.activity.ProxyActivity;
import com.sunxin.core.delegates.CommonDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public CommonDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
