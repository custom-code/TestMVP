package com.zero.test.app.view;

/**
 * Created by ем on 2015/5/9.
 */
public interface LoginView {
    public void showToast(String msg);

    public void showProgress();

    public void hideProgress();

    public void setUserNameError();

    public void setPasswordError();

    public void jumpToMainActivity();
}
