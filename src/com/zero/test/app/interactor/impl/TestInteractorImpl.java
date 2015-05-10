package com.zero.test.app.interactor.impl;

import android.os.Handler;
import com.zero.test.app.interactor.TestInteractor;
import com.zero.test.base.listener.BaseListener;

public class TestInteractorImpl implements TestInteractor {

    @Override
    public void login(String username, String password, BaseListener listener) {

    }

    @Override
    public void getData(BaseListener baseListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseListener.onError();
                baseListener.onFinish();
            }
        }, 2000);
    }
}
