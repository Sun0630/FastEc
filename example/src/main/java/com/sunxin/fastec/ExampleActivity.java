package com.sunxin.fastec;

import com.sunxin.core.activity.ProxyActivity;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public CommonDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
