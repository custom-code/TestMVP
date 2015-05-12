package com.zero.test.app.presenter.impl;

import com.zero.test.app.interactor.MainInteractor;
import com.zero.test.app.interactor.impl.MainInteractorImpl;
import com.zero.test.app.listener.OnLoadDataFinishedListener;
import com.zero.test.app.presenter.MainPresenter;
import com.zero.test.app.view.MainView;

import java.util.List;

/**
 * Created by 哲 on 2015/5/9.
 */
public class MainPresenterImpl implements MainPresenter, OnLoadDataFinishedListener {
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void onResunme() {
        mainView.showProgressBar();
        mainInteractor.getData(this);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onFinish(List<String> items) {
        mainView.hideProgressBar();
        mainView.showData(items);
    }
}
