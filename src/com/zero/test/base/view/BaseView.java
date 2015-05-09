package com.zero.test.base.view;

/**
 * Created by ем on 2015/5/10.
 */
public interface BaseView {

    public void setTitle();

    public void showProgress();

    public void hideProgress();

    public void showMessage();

    public void setDataError();

    public void setDataSuccess();

    public void jumpToActivity();
}
