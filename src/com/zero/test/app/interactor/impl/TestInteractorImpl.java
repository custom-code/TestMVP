package com.zero.test.app.interactor.impl;

import android.os.Handler;
import com.zero.test.app.interactor.TestInteractor;
import com.zero.test.base.listener.BaseInteractorListener;

public class TestInteractorImpl implements TestInteractor {

    @Override
    public void login(String username, String password, BaseInteractorListener listener) {

    }

    @Override
    public void getData(final BaseInteractorListener baseListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseListener.onError();
                baseListener.onFinish();
            }
        }, 1000);
    }
}
