package com.zero.test.app.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;
import com.zero.test.app.interactor.LoginInteractor;
import com.zero.test.app.listener.OnLoginFinishedListener;

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUserNameError();
                } else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                } else {
                    listener.onSuccess();
                }
            }
        }, 1000);
    }
}
