package com.zero.test.app.interactor;

import com.zero.test.base.interactor.BaseInteractor;
import com.zero.test.base.listener.BaseListener;

/**
 * Created by �� on 2015/5/9.
 */
public interface TestInteractor extends BaseInteractor{
    public void login(String username, String password, BaseListener listener);
}
