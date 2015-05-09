package com.zero.test.base.presenter.impl;

import com.zero.test.base.listener.BaseListener;
import com.zero.test.base.presenter.BasePresenter;
import com.zero.test.base.view.BaseView;

/**
 * Created by ем on 2015/5/10.
 */
public abstract class BasePresenterImpl implements BasePresenter, BaseListener {
    private BaseView baseView;

    public BasePresenterImpl(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onStart() {
        baseView.showProgress();
    }

    @Override
    public void onError() {
        baseView.hideProgress();
    }

    @Override
    public void onFinish() {
        baseView.hideProgress();
    }
}
