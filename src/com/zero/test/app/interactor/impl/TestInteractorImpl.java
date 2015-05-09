package com.zero.test.app.interactor.impl;

import android.os.Handler;
import android.text.TextUtils;
import com.zero.test.app.interactor.LoginInteractor;
import com.zero.test.app.interactor.TestInteractor;
import com.zero.test.app.listener.OnLoginFinishedListener;
import com.zero.test.base.listener.BaseListener;

public class TestInteractorImpl implements TestInteractor {
    @Override
    public void login(final String username, final String password, final BaseListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onError();
                } else if (TextUtils.isEmpty(password)) {
                    listener.onError();
                } else {
                    listener.onFinish();
                }
            }
        }, 2000);
    }
}
