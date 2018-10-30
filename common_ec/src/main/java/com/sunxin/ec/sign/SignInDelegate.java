package com.sunxin.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.joanzapata.iconify.widget.IconTextView;
import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sunxin
 * @date 2018/10/30 11:24 AM
 * @desc 注册
 */
public class SignInDelegate extends CommonDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEditSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mEditSignInPassword;
    @BindView(R2.id.btn_sign_in)
    Button mBtnSignIn;
    @BindView(R2.id.tv_sign_in_link)
    AppCompatTextView mTvSignInLink;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView mIconSignInWechat;


    private boolean checkForm() {
        final String email = mEditSignInEmail.getText().toString();
        final String password = mEditSignInPassword.getText().toString();

        boolean isPass = true;



        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEditSignInEmail.setError(null);
        }




        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mEditSignInPassword.setError("密码不少于6位");
            isPass = false;
        } else {
            mEditSignInPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.btn_sign_in)
    public void onViewClickeSignIn() {
        if (checkForm()){

        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    public void onViewClickWechat() {

    }

    @OnClick(R2.id.tv_sign_in_link)
    public void onViewClickLinke(){
        start(new SignUpDelegate());
    }

}
