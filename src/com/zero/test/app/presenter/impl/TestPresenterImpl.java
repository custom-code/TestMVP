package com.zero.test.app.presenter.impl;

import com.zero.test.app.interactor.TestInteractor;
import com.zero.test.app.interactor.impl.TestInteractorImpl;
import com.zero.test.base.presenter.impl.BasePresenterImpl;
import com.zero.test.base.view.BaseView;

/**
 * Created by ем on 2015/5/10.
 */
public class TestPresenterImpl extends BasePresenterImpl {

    private BaseView baseView;
    private TestInteractor testInteractor;

    public TestPresenterImpl(BaseView baseView) {
        super(baseView);
        this.baseView = baseView;
        testInteractor = new TestInteractorImpl();
    }

    @Override
    public void onStart() {
        testInteractor.login("11", "22", this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
        baseView.showMessage();
    }

}
