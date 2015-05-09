package com.zero.test.app.interactor;

import com.zero.test.base.listener.BaseListener;

/**
 * Created by ем on 2015/5/9.
 */
public interface TestInteractor {
    public void login(String username, String password, BaseListener listener);
}
