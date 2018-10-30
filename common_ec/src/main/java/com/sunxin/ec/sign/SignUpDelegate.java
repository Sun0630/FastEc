package com.sunxin.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.sunxin.core.delegates.CommonDelegate;
import com.sunxin.core.net.RestClient;
import com.sunxin.core.net.callback.ISuccess;
import com.sunxin.core.util.log.LatteLogger;
import com.sunxin.ec.R;
import com.sunxin.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author sunxin
 * @date 2018/10/30 10:10 AM
 * @desc 注册
 */
public class SignUpDelegate extends CommonDelegate {

    public static final String TAG = "SignUpDelegate";

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mEditSignUpName;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEditSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mEditSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mEditSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mEditSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    Button mBtnSignUp;
    @BindView(R2.id.tv_sign_up_link)
    AppCompatTextView mTvSignUpLink;

    private ISignListener mSignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mSignListener = (ISignListener) activity;
        }
    }

    private boolean checkForm() {
        final String name = mEditSignUpName.getText().toString();
        final String email = mEditSignUpEmail.getText().toString();
        final String phone = mEditSignUpPhone.getText().toString();
        final String password = mEditSignUpPassword.getText().toString();
        final String rePassword = mEditSignUpRePassword.getText().toString();

        boolean isPass = true;

        if (TextUtils.isEmpty(name)) {
            mEditSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            mEditSignUpName.setError(null);
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditSignUpEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEditSignUpEmail.setError(null);
        }

        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            mEditSignUpPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mEditSignUpPhone.setError(null);
        }


        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mEditSignUpPassword.setError("密码不少于6位");
            isPass = false;
        } else {
            mEditSignUpPassword.setError(null);
        }

        if (TextUtils.isEmpty(rePassword) || rePassword.length() < 6 || !rePassword.equals(password)) {
            mEditSignUpRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mEditSignUpRePassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick({R2.id.btn_sign_up})
    public void onViewClickedSignUp() {
        signUp();
    }


    @OnClick(R2.id.tv_sign_up_link)
    public void onViewClickLinked() {
        start(new SignInDelegate());
    }

    private void signUp() {
        if (checkForm()) {
            RestClient
                    .builder()
                    .url("http://127.0.0.1/user_profile")
                    .params("name", mEditSignUpName.getText().toString())
                    .params("email", mEditSignUpEmail.getText().toString())
                    .params("phone",mEditSignUpPhone.getText().toString())
                    .params("password",mEditSignUpPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json(TAG, response);
                            // 持久化到本地，一般注册完就登陆了
                            SignHandler.onSignUp(response,mSignListener);
                        }
                    })
                    .build()
                    .post();

        }
    }
}
