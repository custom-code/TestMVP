package com.zero.test.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.zero.test.R;
import com.zero.test.app.presenter.LoginPresenter;
import com.zero.test.app.presenter.impl.LoginPresenterImpl;
import com.zero.test.app.view.LoginView;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private Context context;
    private EditText mEtvUser, mEtvPwd;
    private Button mBtnSubmit;
    private ProgressBar mProgressBar;
    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginActivity.this;
        setContentView(R.layout.login_layout);
        loginPresenter = new LoginPresenterImpl(this);
        initView();
    }

    private void initView() {
        mEtvUser = (EditText) findViewById(R.id.login_user);
        mEtvPwd = (EditText) findViewById(R.id.login_pwd);
        mBtnSubmit = (Button) findViewById(R.id.login_submit);
        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);
        mBtnSubmit.setOnClickListener(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameError() {
        mEtvUser.setError(getString(R.string.login_user_error));
    }

    @Override
    public void setPasswordError() {
        mEtvPwd.setError(getString(R.string.login_pwd_error));
    }

    @Override
    public void jumpToMainActivity() {
        startActivity(new Intent(context, TestActivity.class));
//        startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_submit:
                loginPresenter.validateCredentials(mEtvUser.getText().toString(), mEtvPwd.getText().toString());
                break;
        }
    }
}
