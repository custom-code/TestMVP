package com.zero.test.app.listener;

/**
 * Created by 哲 on 2015/5/9.
 */
public interface OnLoginFinishedListener {
    void onUserNameError();

    void onPasswordError();

    void onSuccess();
}
