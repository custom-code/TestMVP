package com.zero.test.app.presenter.impl;

import com.zero.test.app.interactor.TestInteractor;
import com.zero.test.app.interactor.impl.TestInteractorImpl;
import com.zero.test.app.presenter.TestPresenter;
import com.zero.test.base.listener.BaseInteractorListener;
import com.zero.test.base.view.BaseView;

/**
 * Created by 哲 on 2015/5/10.
 */
public class TestPresenterImpl implements TestPresenter, BaseInteractorListener {

    private BaseView baseView;
    private TestInteractor testInteractor;

    public TestPresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        testInteractor = new TestInteractorImpl();
    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onStart() {
        baseView.showProgress();
        testInteractor.getData(this);
    }

    @Override
    public void onError() {
        baseView.setDataError();
    }

    @Override
    public void onFinish() {
        baseView.hideProgress();
        baseView.showMessage();
        baseView.setDataSuccess();
        baseView.jumpToActivity();
    }

}
