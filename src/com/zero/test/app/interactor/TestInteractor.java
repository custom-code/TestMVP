package com.zero.test.app.interactor;

import com.zero.test.base.interactor.BaseInteractor;
import com.zero.test.base.listener.BaseInteractorListener;

/**
 * Created by ем on 2015/5/9.
 */
public interface TestInteractor extends BaseInteractor{
    public void login(String username, String password, BaseInteractorListener listener);
}
