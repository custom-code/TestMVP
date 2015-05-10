package com.zero.test.base.view;

/**
 * Created by ем on 2015/5/10.
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMessage();

    void setDataError();

    void setDataSuccess();

    void jumpToActivity();
}
