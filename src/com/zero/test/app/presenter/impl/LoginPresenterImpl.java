package com.zero.test.app.presenter.impl;

import com.zero.test.app.interactor.LoginInteractor;
import com.zero.test.app.interactor.impl.LoginInteractorImpl;
import com.zero.test.app.listener.OnLoginFinishedListener;
import com.zero.test.app.presenter.LoginPresenter;
import com.zero.test.app.view.LoginView;


public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String userName, String password) {
        loginView.showProgress();
        loginInteractor.login(userName, password, this);
    }

    @Override
    public void onUserNameError() {
        loginView.hideProgress();
        loginView.setUserNameError();
    }

    @Override
    public void onPasswordError() {
        loginView.hideProgress();
        loginView.setPasswordError();
    }

    @Override
    public void onSuccess() {
        loginView.hideProgress();
        loginView.showToast("登陆成功");
        loginView.jumpToMainActivity();
    }
}
