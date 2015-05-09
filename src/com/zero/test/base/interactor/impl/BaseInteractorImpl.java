package com.zero.test.base.interactor.impl;

import com.zero.test.base.interactor.BaseInteractor;
import com.zero.test.base.listener.BaseListener;

/**
 * Created by ем on 2015/5/10.
 */
public class BaseInteractorImpl implements BaseInteractor {

    @Override
    public void getData(BaseListener baseListener) {
        baseListener.onError();
        baseListener.onFinish();
    }
}
