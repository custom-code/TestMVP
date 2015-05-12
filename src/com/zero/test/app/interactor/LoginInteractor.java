package com.zero.test.app.interactor;

import com.zero.test.app.listener.OnLoginFinishedListener;

/**
 * Created by 哲 on 2015/5/9.
 */
public interface LoginInteractor {
    void login(String username, String password, OnLoginFinishedListener listener);
}
