package com.sunxin.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.sunxin.core.activity.ProxyActivity;
import com.sunxin.core.app.ConfigType;
import com.sunxin.core.app.Globle;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.ui.launcher.ILauncherFinishListener;
import com.sunxin.core.ui.launcher.OnLauncherFinishTag;
import com.sunxin.ec.launcher.LauncherDelegate;
import com.sunxin.ec.main.EcBottomDelegate;
import com.sunxin.ec.sign.ISignListener;
import com.sunxin.ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherFinishListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        Globle.getConfigrations().put(ConfigType.ACTIVITY.name(), this);
        if (actionBar != null) {
            actionBar.hide();
        }
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public CommonDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLauncherFinished(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登陆了", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没有登陆", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
