package com.zero.test.app.view;

/**
 * Created by 哲 on 2015/5/9.
 */
public interface LoginView {
    void showToast(String msg);

    void showProgress();

    void hideProgress();

    void setUserNameError();

    void setPasswordError();

    void jumpToMainActivity();
}
